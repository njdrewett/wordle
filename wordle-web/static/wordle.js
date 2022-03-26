const tileContainer = document.querySelector('.tile-container');

const keyContainer = document.querySelector('.key-container');

const messageContainer = document.querySelector('.message-container');

const keyRowTop = document.querySelector('.key-row-top');
const keyRowMiddle = document.querySelector('.key-row-middle');
const keyRowBottom = document.querySelector('.key-row-bottom');

let word

const getWord = () => {
    fetch('http://'+PROXY_URL+'/word')
    .then(response => response.json())
    .then(json => {
        console.log(json);
        word = json.toLowerCase();
        })
        .catch(err => console.log(err));
}

getWord();

const keysTop = [
    'Q','W','E','R','T','Y','U','I','O','P'
    ]

const keysMiddle = [
    'A','S','D','F','G','H','J','K','L'
]

const keysBottom = [
    'ENTER','Z','X','C','V','B','N','M','<<'
]


const maxGuesses = 6;
const wordLength = 5;

// Guess Rows array
const guessRows = new Array();
for( var i = 0; i < maxGuesses; ++i) {
        guessRow = new Array();
        for ( var j = 0; j < wordLength; ++j) {
            guessRow.push('');
        }
        guessRows.push(guessRow);
    }
let currentTileRow = 0;
let currentTileRowIndex = 0;
let gameOver = false;

// Initialise the tile rows container
guessRows.forEach((guessRow, guessRowIndex) => {
    const rowDiv = document.createElement('div');
    rowDiv.setAttribute('id', 'guess-row-' + guessRowIndex);
    guessRow.forEach((guess, guessIndex) => {
        const tileDiv = document.createElement('div');
        tileDiv.setAttribute('id','guess-row-'+ guessRowIndex + '-' + guessIndex);
        tileDiv.classList.add('tile');
        rowDiv.append(tileDiv);
    })
    tileContainer.append(rowDiv);
})

const handleKeyClicked = (key) => {
    console.log('Clicked ', key)
    if(gameOver) {
        return;
    }
    if(key === '<<') {
        console.log('Delete letter');
        deleteLetter();
        console.log(guessRows);
        return ;
    }
    if(key === 'ENTER') {
        console.log('Enter clicked');
        submitRow();
        return ;
    }

    addLetterToGuessTile(key);
}

const addKey = (key, row) => {
    const buttonElement = document.createElement('button')
    buttonElement.textContent =key;
    buttonElement.setAttribute('id', key);
    buttonElement.addEventListener('click', () => handleKeyClicked(key));
    row.append(buttonElement);
}

keysTop.forEach(key => addKey(key, keyRowTop))
keysMiddle.forEach(key => addKey(key, keyRowMiddle))
keysBottom.forEach(key => addKey(key, keyRowBottom))

const addLetterToGuessTile = (letter) => {
    if(currentTileRowIndex < wordLength && currentTileRow < maxGuesses) {
        const tile = document.getElementById('guess-row-'+ currentTileRow + '-' + currentTileRowIndex);
        tile.textContent = letter;
        tile.setAttribute('data',letter);
        guessRows[currentTileRow][currentTileRowIndex] = letter;
        currentTileRowIndex ++;
        console.log(currentTileRow + ' , ' + currentTileRowIndex );
    }
}

const deleteLetter = () => {
    if(currentTileRowIndex > 0) {
     currentTileRowIndex --;
     const tile = document.getElementById('guess-row-'+ currentTileRow + '-' + currentTileRowIndex);
     tile.textContent = '';
     tile.setAttribute('data','');
     guessRows[currentTileRow][currentTileRowIndex] = '';
     }
}

const submitRow = () => {
    if(currentTileRowIndex === wordLength) {

        const guess = guessRows[currentTileRow].join('');
        console.log('Guess = '+ guess);
        fetch(`http://`+PROXY_URL+`/checkword/?word=${guess}`)
            .then(response => response.json())
            .then(json => {
                console.log("json response" + json)
                if(json == false) {
                    showMessage("Not A Word");
                    return;
                } else {
                    flipTile();
                    console.log('Guess is: ' + guess + ' Word should be ' + word );
                    if(word.toLowerCase() == guess.toLowerCase()) {
                        showMessage('Correct!');
                        gameOver = true;
                    } else {
                        showMessage('Wrong!');
                        currentTileRow ++;
                        currentTileRowIndex = 0;
                        if(currentTileRow > maxGuesses - 1 ) {
                            gameOver = true;
                            showMessage('Game Over!')
                        }
                        return;
                    }
                }
            }).catch(error => {
                console.log(error);
            })
     }
}

const addColourToKey = (letter, colour) => {
    const key = document.getElementById(letter);
    key.classList.add(colour);
}

const flipTile = () => {
    console.log('guess-row-'+ currentTileRow);
    const rowTiles = document.querySelector('#guess-row-'+ currentTileRow).childNodes;
    let checkWord = word;
    const guess = [];

    // TODO: This needs refining to be more accurate to actual wordle..

    // all letters are wrong until checked
    rowTiles.forEach(tile => {
        guess.push({ letter: tile.getAttribute('data'), colour:'grey-overlay'});
    })

    // if letter is correct and in same position, its green and set position of letter to blank to remove duplicates
    guess.forEach((guess, index) => {
        if(guess.letter.toLowerCase() == word[index].toLowerCase()) {
            guess.colour = 'green-overlay';
            checkWord = checkWord.replace(guess.letter, '');
        }
    })

    // if present but not correct position, set yellow and remove to stop duplicates
    guess.forEach(guess => {
        if(checkWord.toLowerCase().includes(guess.letter.toLowerCase()) && (guess.colour != 'green-overlay')) {
            guess.colour = 'yellow-overlay';
            checkWord = checkWord.replace(guess.letter,'');
        }
    })

    // Set result and update keyboard key
    rowTiles.forEach((tile,index) => {
        setTimeout(() => {
            tile.classList.add('flip');
            tile.classList.add(guess[index].colour);
            addColourToKey(guess[index].letter,guess[index].colour);
         }, 500 * index);
    })
}

const showMessage = (message) => {
    const messageElement = document.createElement('p');
    messageElement.textContent = message;
    messageContainer.append(messageElement);
    setTimeout(()=> messageContainer.removeChild(messageElement), 2000);
}