public class Configurations {

    // Class attributes defining the board dimensions and win conditions
    private int board_size;
    private int length_to_win;
    private int max_levels;

    // 2D array to represent the game board
    private char[][] board;

    // Constructor to initialize board dimensions and game configurations
    public Configurations(int board_size, int length_to_win, int max_levels) {
        this.board_size = board_size;
        this.length_to_win = length_to_win;
        this.max_levels = max_levels;
    
        // Initialize the board with the specified size and set each square to a space character
        board = new char[board_size][board_size];
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                board[i][j] = ' ';  
            }
        }

    }
     // Creates a HashDictionary with an initial size of 8001
    public HashDictionary createDictionary() {
        return new HashDictionary(8001);
    }

     // Concatenate board contents into a single string for hashing
    public int repeatedConfiguration(HashDictionary hashTable) {
        String config = "";
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                config += Character.toString(board[r][c]);
            }
        }
        
        if (hashTable.get(config) != -1){
            return hashTable.get(config);
        }
        return -1;
    }

    public void addConfiguration(HashDictionary hashDictionary, int score) {
        String config = "";
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                config += Character.toString(board[r][c]);
            }
        }
        Data record = new Data(config, score);

        hashDictionary.put(record);
    }
     // Places a symbol on the board at specified row and column
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public boolean squareIsEmpty(int row, int col) {
        if (board[row][col] == ' ') {
            return true;
        }
        return false;
    }
     // Method used to o check if a player with the specified symbol has won
    public boolean wins(char symbol) {
        //horizontal
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c <= board_size - length_to_win; c++) {
                boolean win = true;
                for (int i = 0; i < length_to_win; i++) {
                    if (board[r][c + i] != symbol) {
                        win = false;
                        break;
                    }
                }
                if (win) {
                    return true;
                }
            }
        }
        // vertically
        for (int c = 0; c < board_size; c++) {
            for (int r = 0; r <= board_size - length_to_win; r++) {
                boolean win = true;
                for (int i = 0; i < length_to_win; i++) {
                    if (board[r + i][c] != symbol) {
                        win = false;
                        break;
                    }
                }

                if (win) {
                    return true;
                }
            }
        }
        //diagonally
        for (int r = 0; r <= board_size - length_to_win; r++) {
            for (int c = 0; c <= board_size - length_to_win; c++) {
                boolean won2 = true;
                for (int i = 0; i < length_to_win; i++) {
                    if (board[r+1][c+1] != symbol) {
                        won2 = false;
                    }
                }

                if (won2) {
                    return true;
                }
            }
        }
        return false;
    }
    

    public boolean isDraw() {
        for (int r = 0; r < board_size; r++) {
            for (int c = 0; c < board_size; c++) {
                if (board[r][c] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    // Evaluates the current state of the board
    public int evalBoard() {
        if (wins('0')) {
            return 3;
        } else if (wins('X')) {
            return 0;
        } else if (isDraw()) {
            return 2;
        } else {
            return 1;
        }
    }


 }
 