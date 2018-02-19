package sample.Board;


import sample.Exception.WrongFieldException;

import static sample.App.GUI.board;

public class Move {
    private int counter = 0;

    int checkLeftX(Piece piece, int x, int y) throws WrongFieldException {
        //checks to the left from new piece
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

    int checkRightX(Piece piece, int x, int y)throws WrongFieldException {
        //checks to the right from new piece
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

    int checkUpY(Piece piece, int x, int y) throws WrongFieldException {
        //checks up from the new piece
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

    int checkDownY(Piece piece, int x, int y) throws WrongFieldException {
        //checks down from the new piece
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

    int checkDiagonalRightDown(Piece piece, int x, int y) throws WrongFieldException {
        //checks right and down from the new piece
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

    int checkDiagonalRightUp(Piece piece, int x, int y) throws WrongFieldException {
        //checks right and up from the new piece
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

    int checkDiagonalLeftDown(Piece piece, int x, int y) throws WrongFieldException {
        //checks left and down from the new piece
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


    int checkDiagonalLeftUp(Piece piece, int x, int y) throws WrongFieldException {
        //checks left and up from the new piece
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

    public static void countPieces(){
        for(int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j].hasPiece())
                {
                    if(board[i][j].getPiece().getType()==PieceType.BLACK)
                        Tile.countBlack++;
                    else
                        Tile.countWhite++;
                }
            }
        }
    }

}

