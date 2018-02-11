package sample.Board;


import static sample.ReversiApp.board;

public class Move {
    private int counter = 0;

    public int checkLeftX(Piece piece, int x, int y){
        //sprawdza w lewo od nowego pionka
        int i = x-1;
        int j = x-1;
        counter = 0;

        if (i >= 2) {
            if (board[i][y].hasPiece() && board[i][y].getPiece().getType() != piece.getType()) {
                while (board[i][y].hasPiece()) {
                    if (board[i - 1][y].hasPiece() && board[i - 1][y].getPiece().getType() == piece.getType()) {
                        if (j >= i) {
                            while (board[j][y].hasPiece()) {
                                if (board[j - 1][y].hasPiece()) {
                                    if (board[j][y].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[j][y].getPiece());
                                        counter++;
                                    }

                                    if (board[j - 1][y].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j > 0) j--;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i > 1) i--;
                    else break;
                }
            }
        }
        else if (i == 1) {
            if (board[i][y].hasPiece() && board[i - 1][y].hasPiece() && board[i - 1][y].getPiece().getType() == piece.getType()) {
                board[i][y].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

    public int checkRightX(Piece piece, int x, int y){
        //sprawdza w prawo od nowego pionka
        int i = x+1;
        int j = x+1;
        counter = 0;

        if (i <= 5) {
            if (board[i][y].hasPiece() && board[i][y].getPiece().getType() != piece.getType()) {
                while (board[i][y].hasPiece()) {
                    if (board[i + 1][y].hasPiece() && board[i + 1][y].getPiece().getType() == piece.getType()) {
                        if (j <= i) {
                            while (board[j][y].hasPiece()) {
                                if (board[j + 1][y].hasPiece()) {
                                    if (board[j][y].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[j][y].getPiece());
                                        counter++;
                                    }

                                    if (board[j + 1][y].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j < 7) j++;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i < 6) i++;
                    else break;
                }
            }
        }
        else if (i == 6) {
            if (board[i][y].hasPiece() && board[i + 1][y].hasPiece() && board[i + 1][y].getPiece().getType() == piece.getType()) {
                board[i][y].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

    public int checkUpY(Piece piece, int x, int y){
        //sprawdza w gore od nowego pionka
        int i = y-1;
        int j = y-1;
        counter = 0;

        if(i >=2) {
            if (board[x][i].hasPiece() && board[x][i].getPiece().getType() != piece.getType()) {
                while (board[x][i].hasPiece()) {
                    if (board[x][i - 1].hasPiece() && board[x][i - 1].getPiece().getType() == piece.getType()) {
                        if (j >= i) {
                            while (board[x][j].hasPiece()) {
                                if (board[x][j - 1].hasPiece()) {
                                    if (board[x][j].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[x][j].getPiece());
                                        counter++;
                                    }

                                    if (board[x][j - 1].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j > 0) j--;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i > 1) i--;
                    else break;
                }
            }
        }
        else if (i == 1) {
            if (board[x][i].hasPiece() && board[x][i - 1].hasPiece() && board[x][i - 1].getPiece().getType() == piece.getType()) {
                board[x][i].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

    public int checkDownY(Piece piece, int x, int y){
        //sprawdza w dol od nowego pionka
        int i = y+1;
        int j = y+1;
        counter = 0;

        if(i <=5) {
            if(board[x][i].hasPiece() && board[x][i].getPiece().getType() != piece.getType()) {
                while (board[x][i].hasPiece()) {
                    if (board[x][i + 1].hasPiece() && board[x][i + 1].getPiece().getType() == piece.getType()) {
                        if (j <= i) {
                            while (board[x][j].hasPiece()) {
                                if (board[x][j + 1].hasPiece()) {
                                    if (board[x][j].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[x][j].getPiece());
                                        counter++;
                                    }

                                    if (board[x][j + 1].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j < 7) j++;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i < 6) i++;
                    else break;
                }
            }
        }
        else if(i == 6){
            if(board[x][i].hasPiece() && board[x][i+1].hasPiece() && board[x][i+1].getPiece().getType() == piece.getType()){
                board[x][i].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

    public int checkDiagonalRightDown(Piece piece, int x, int y){
        //sprawdza w prawo i dol od nowego pionka: x+1 y+1
        int i = x+1;
        int j = x+1;
        int k = y+1;
        int m = y+1;
        counter = 0;

        if(i <=5 && k <=5) {
            if(board[i][k].hasPiece() && board[i][k].getPiece().getType() != piece.getType()) {
                while (board[i][k].hasPiece()) {
                    if (board[i + 1][k + 1].hasPiece() && board[i + 1][k + 1].getPiece().getType() == piece.getType()) {
                        if (j <= i || m <= k) {
                            while (board[j][m].hasPiece()) {
                                if (board[j + 1][m + 1].hasPiece()) {
                                    if (board[j][m].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[j][m].getPiece());
                                        counter++;
                                    }

                                    if (board[j + 1][m + 1].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j < 7) j++;
                                else break;
                                if (m < 7) m++;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i < 6) i++;
                    else break;
                    if (k < 6) k++;
                    else break;
                }
            }
        }
        else if((i==6 && k<=5) || (k==6 && i<=5) || (i==6 && k==6)){
            if(board[i][k].hasPiece() && board[i+1][k+1].hasPiece() && board[i+1][k+1].getPiece().getType() == piece.getType()){
                board[i][k].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

    public int checkDiagonalRightUp(Piece piece, int x, int y){
        //sprawdza w prawo i gore od nowego pionka: x+1 y-1
        int i = x+1;
        int j = x+1;
        int k = y-1;
        int m = y-1;
        counter = 0;

        if(i <=5 && k >=2) {
            if(board[i][k].hasPiece() && board[i][k].getPiece().getType() != piece.getType()) {
                while (board[i][k].hasPiece()) {
                    if (board[i + 1][k - 1].hasPiece() && board[i + 1][k - 1].getPiece().getType() == piece.getType()) {
                        if (j <= i || m >= k) {
                            while (board[j][m].hasPiece()) {
                                if (board[j + 1][m - 1].hasPiece()) {
                                    if (board[j][m].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[j][m].getPiece());
                                        counter++;
                                    }

                                    if (board[j + 1][m - 1].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j < 7) j++;
                                else break;
                                if (m > 0) m--;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i < 6) i++;
                    else break;
                    if (k > 1) k--;
                    else break;
                }
            }
        }
        else if((i==6 && k >=2) || (k==1 && i<=5) || (i==6 && k==1)){
            if(board[i][k].hasPiece() && board[i+1][k-1].hasPiece() && board[i+1][k-1].getPiece().getType() == piece.getType()){
                board[i][k].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

    public int checkDiagonalLeftDown(Piece piece, int x, int y){
        //sprawdza w lewo i dol od nowego pionka: x-1 y+1
        int i = x-1;
        int j = x-1;
        int k = y+1;
        int m = y+1;
        counter = 0;

        if(i >=2 && k <=5) {
            if(board[i][k].hasPiece() && board[i][k].getPiece().getType() != piece.getType()) {
                while (board[i][k].hasPiece()) {
                    if (board[i - 1][k + 1].hasPiece() && board[i - 1][k + 1].getPiece().getType() == piece.getType()) {
                        if (j >= i || m <= k) {
                            while (board[j][m].hasPiece()) {
                                if (board[j - 1][m + 1].hasPiece()) {
                                    if (board[j][m].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[j][m].getPiece());
                                        counter++;
                                    }

                                    if (board[j - 1][m + 1].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j > 0) j--;
                                else break;
                                if (m < 7) m++;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i > 1) i--;
                    else break;
                    if (k < 6) k++;
                    else break;
                }
            }
        }
        else if((i==1 && k <=5) || (k==6 && i>=2) || (i==1 && k==6)){
            if(board[i][k].hasPiece() && board[i-1][k+1].hasPiece() && board[i-1][k+1].getPiece().getType() == piece.getType()){
                board[i][k].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }


    public int checkDiagonalLeftUp(Piece piece, int x, int y){
        //sprawdza w lewo i gore od nowego pionka: x-1 y-1
        int i = x-1;
        int j = x-1;
        int k = y-1;
        int m = y-1;
        counter = 0;

        if(i >=2 && k >=2) {
            if(board[i][k].hasPiece() && board[i][k].getPiece().getType() != piece.getType()) {
                while (board[i][k].hasPiece()) {
                    if (board[i - 1][k - 1].hasPiece() && board[i - 1][k - 1].getPiece().getType() == piece.getType()) {
                        if (j >= i || m >= k) {
                            while (board[j][m].hasPiece()) {
                                if (board[j - 1][m - 1].hasPiece()) {
                                    if (board[j][m].getPiece().getType() != piece.getType()) {
                                        Tile.changePieceList.add(board[j][m].getPiece());
                                        counter++;
                                    }

                                    if (board[j - 1][m - 1].getPiece().getType() == piece.getType()) {
                                        for (Piece changePiece : Tile.changePieceList) {
                                            changePiece.setType(piece.getType());
                                        }
                                        Tile.changePieceList.clear();
                                        break;
                                    }
                                }
                                if (j > 0) j--;
                                else break;
                                if (m > 0) m--;
                                else break;
                            }
                        }
                        break;
                    }
                    if (i > 1) i--;
                    else break;
                    if (k > 1) k--;
                    else break;
                }
            }
        }
        else if((i==1 && k >=2) || (k==1 && i>=2) || (i==1 && k==1)){
            if(board[i][k].hasPiece() && board[i-1][k-1].hasPiece() && board[i-1][k-1].getPiece().getType() == piece.getType()){
                board[i][k].getPiece().setType(piece.getType());
            }
        }
        return counter;
    }

}

