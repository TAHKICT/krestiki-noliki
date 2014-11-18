package GameOption;

/**
 * Created with IntelliJ IDEA.
 * User: админ
 * Date: 16.11.14
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
//Class with game logic
public class GameOptions {
    private String gameURL;  //game URL
    private String player1;
    private String player2;
    private boolean turn;    //player turns: player1-true,player2-false
    private char[][] gameBoard;
    private boolean sessionfull;  //when we can start our game
    private String currentPlayerName;  //player,who make step
    private String winner;


    //constructor
    public GameOptions(String gameURL, String player1){
        this.gameURL = gameURL;
        this.player1 = player1;
        this.turn = true;
        currentPlayerName = player1;
        this.gameBoard = new char[3][3];
        this.sessionfull = false;
        this.winner = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.gameBoard[i][j] = '-';
            }
        }
    }
    // getters and setters for our Game Controller
    public String getCurrentPlayerName() {
        return currentPlayerName;
    }
    public String getWinner(){
        return winner;
    }

    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;
    }
    public String getGameURL() {
        return gameURL;
    }
    public String getPlayer1() {
        return player1;
    }
    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
        this.sessionfull = true;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public boolean getSessionfull() {
        return sessionfull;
    }
    //our main method with game logic
    public boolean MakeStep(String player, int kor1, int kor2){
        int x = kor1;
        int y = kor2;
        boolean done = false;
            if(CanMakeStep(x,y)){
                if (gameBoard[x][y] == '-' &&  x >= 0 && x < 3
                        && y >= 0 && y < 3) {
                    if (player.equals(player1)){
                    gameBoard[x][y] = 'x';
                    }
                     else if (player.equals(player2)){
                    gameBoard[x][y] = '0';
                    }
                    done = true;
                    }
                 turn = !turn;
            }
        return done;
    }
    //checking about possibility of making move on specific coordinates
    public boolean CoordinatesStepPossibility(int x, int y){
        if(gameBoard[x][y] == '-'){
            return true;
        }else
            return false;
    }
    //checking about possibility of making a game step
    public boolean CanMakeStep(int x, int y){
        boolean p = false;    //by default - you can't
        //---checking. is there a winner---------
        if(        gameBoard[0][0] == 'x' && gameBoard[0][1] == 'x' && gameBoard[0][2] == 'x'
                || gameBoard[1][0] == 'x' && gameBoard[1][1] == 'x' && gameBoard[1][2] == 'x'
                || gameBoard[2][0] == 'x' && gameBoard[2][1] == 'x' && gameBoard[2][2] == 'x'
                || gameBoard[0][0] == 'x' && gameBoard[1][0] == 'x' && gameBoard[2][0] == 'x'
                || gameBoard[0][1] == 'x' && gameBoard[1][1] == 'x' && gameBoard[2][1] == 'x'
                || gameBoard[0][2] == 'x' && gameBoard[1][2] == 'x' && gameBoard[2][2] == 'x'
                || gameBoard[0][0] == 'x' && gameBoard[1][1] == 'x' && gameBoard[2][2] == 'x'
                || gameBoard[2][0] == 'x' && gameBoard[1][1] == 'x' && gameBoard[0][2] == 'x'){
            winner = player1;       //if exist 1 of 8 wins combinations with 'x' => player1 is winner!
        }else if(gameBoard[0][0] == '0' && gameBoard[0][1] == '0' && gameBoard[0][2] == '0'
                || gameBoard[1][0] == '0' && gameBoard[1][1] == '0' && gameBoard[1][2] == '0'
                || gameBoard[2][0] == '0' && gameBoard[2][1] == '0' && gameBoard[2][2] == '0'
                || gameBoard[0][0] == '0' && gameBoard[1][0] == '0' && gameBoard[2][0] == '0'
                || gameBoard[0][1] == '0' && gameBoard[1][1] == '0' && gameBoard[2][1] == '0'
                || gameBoard[0][2] == '0' && gameBoard[1][2] == '0' && gameBoard[2][2] == '0'
                || gameBoard[0][0] == '0' && gameBoard[1][1] == '0' && gameBoard[2][2] == '0'
                || gameBoard[2][0] == '0' && gameBoard[1][1] == '0' && gameBoard[0][2] == '0'){
            winner = player2;      //if exist 1 of 8 wins combinations with 'y' => player2 is winner!
        }   else {
        //if there isn't a winner, checking about free space on the board
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (gameBoard[i][j] == '-') {
                        p = true;
                        break;
                    }
                }
                if (p = true) {
                    break;
                }
            }
        }
        if (CoordinatesStepPossibility(x,y)) //one more checking about possibility of moving to specific coordinates
            p = true;
        else
            p = false;
       return p;         //result returning
    }
    public boolean gameOver(){
         boolean p = true;
         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j] == '-') {
                    p = false;
                    break;
                }
            }

         }
         return p;

    }
}
