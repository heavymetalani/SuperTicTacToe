import java.io.*;
import java.util.*;
public class SuperTicTacToe {
 public static char player=90;
 public static char[][] board= new char[999][999];
 public static Scanner bol = new Scanner(System.in);
 public static int nplayers,e=0,error=0;
 public static int winseq,co=0;
 public static int count=2;
 public static Formatter savefile;
 public static File infile;
 public static FileReader fr;
 public static int row, col,r,c;
 public static void main(String args[]) {
	 
	 
	 String save,filename,prompt1;
	 String prompt2=new String();
	 do{														//Loop to start a new game or continue from a saved file
		 System.out.println("Press s to open a saved game, any other key to start a new game");
		 save=bol.next();
		 if(save.contains("s")) {								
			 System.out.println("Enter filename.");
			 filename=bol.next();
			 e=opensavedfile(filename);
			 
		 	}
		 else {
			 promptrowcol();
			 promptplayers();
			 promptwinsequence();
			 initgame(row, col);
			 e=0;
		 }
	 	} while(e==1);
	 disp(row,col);
	 //loop
	 do {						//Loop for when the game starts and it only ends when player saves file, wins or it's a draw
		 if(count==nplayers+1) {
			 System.out.println("Press s to save file and exit, press any other key to continue");
			 prompt1=bol.next();
			 if(prompt1.contains("s")) {
				 System.out.println("Enter filename to save it to.");
				 prompt2=bol.next();
				 makefile(prompt2);
				 writefile(row,col);
				 closefile();
				 System.exit(0);
			 }
		 }
	 co=0;
	 forwardplayer();				
	 playerchance();
	 updateboard(r-1,c-1,row,col);
	 if(checktie(row,col)) {
		 break;
	 }
	 }while(!checkwin(colwin(row,col),rowwin(row,col),diagonewin(row,col),diagtwowin(row,col)));
	 
	 if(checktie(row,col)) {
		 System.out.println("The game is tie. Nobody wins.");
	 }
	 else{
		 System.out.println("Player "+player+" won."); 
	 }
 }
 
 public static void disp(int row, int col) {  		//Function to display board
	 for(int j=0;j<col;j++) {
		 if(j==0) {
			 System.out.print("    "+(j+1));
		 }
		 else if(j<=9){
			 System.out.print("   "+(j+1));
		 }else if(j<99) {
			 System.out.print("  "+(j+1));
		 }else {
			 System.out.print(" "+(j+1));
		 }
	 }
	 System.out.println("");
	 for(int i=0;i<row;i++) {
		 if(i<9) {System.out.print((i+1)+"  ");}
		 else if(i<99) {System.out.print((i+1)+" ");}
		 else {System.out.print(i+1);}
		 
			 for(int k=0;k<col;k++) {
				 if(k==col-1) {
		 System.out.print(" "+board[i][k]+" ");
				 }
				 else {
					 System.out.print(" "+board[i][k]+" |");
				 }
		 }
		 System.out.println("");
		 if(i!=row-1) {
		 for(int k=0;k<col;k++) {
			 
			 if(k==col-1) {
				 System.out.print("---");
			 }
			 else if(k==0){
				 System.out.print("   ---+");
			 }
			 else {
				 System.out.print("---+");
			 }
			 
		 }
			 
		 System.out.println("");
		 }
	    
	 }
 }
 public static void initgame(int row, int col) { 		//Function to set all places to ' '
	 for(int i=0;i<row;i++) {
		 for(int j=0; j<col; j++) {
			 board[i][j]=' ';
		 }
	 }
 }
 public static boolean rowwin(int row, int col) {  		//Function to check if there's any win in the row
	 for(int i=0;i<row;i++) {
		 for(int j=0; j<col; j++) {
//			 if (board[i][j]==player) {
//				 if(board[i][j+1]==player) {
//					 if(board[i][j+2]==player) {
//						 return true;
//					 }
//				 }
//			 }
			 for(int k=0;k<winseq;k++) {
				 if(board[i][j+k]==player) {
					 co++;
					 if(co==winseq) {
						 return true;
					 }
				 }
				 else {
					 co=0;
				 }
			 }
		 }
	 }
	return false;
 }
 public static boolean colwin(int row, int col) {		//Function to check if there's any win in the column
	 for(int i=0;i<row;i++) {
		 for(int j=0; j<col; j++) {
//			 if (board[i][j]==player) {
//				 if(board[i+1][j]==player) {
//					 if(board[i+2][j]==player) {
//						 return true;
//					 }
//				 }
//			 }
			 for(int k=0;k<winseq;k++) {
				 if(board[i+k][j]==player) {
					 co++;
					 if(co==winseq) {
						 return true;
					 }
				 }
				 else {
					 co=0;
				 }
			 }
		 }
	 }
	return false;
 }
 public static void forwardplayer() {			//Function to switch player to the next player
	if(count<=nplayers) {
	 if(player==88) {
		 player-=9;
		 count++;
	 } else if(player==79) {
		 player-=14;
		 count++;
	 } else if(player==78) {
		 player+=2;
		 count++;
	 } else if(player==87) {
		 player+=2;
		 count++;
	 } else if(player==90) {
		 player-=2;
	 } else {
		 player++;
		 count++;
	 }
	}
	else {
		player=88;
		count=2;
	}
 }

 public static void updateboard(int r, int c, int row, int col) {		//Function to update the board using user's inputs
	 if(board[r][c]!=' ') {
		 System.out.println("Can't choose this spot. Choose another.");
		 System.out.println("Player "+player+" enter row and column");
		 int ro=bol.nextInt();
		 int co=bol.nextInt();
		 updateboard(ro-1,co-1,row,col);
	 }
	 else {board[r][c]=player;
	 disp(row,col);
	 }
 }
 public static boolean checkwin(boolean colwin, boolean rowwin, boolean diagonewin, boolean diagtwowin) {  //function to check if there's a win
	if(colwin||rowwin||diagonewin||diagtwowin) {
		return true;
	}
	return false;
 }
 public static boolean diagonewin(int row, int col) {			//Function to check win in the negative slope diagonal
	 for(int i=0;i<row;i++) {
		 for(int j=0; j<col; j++) {
			 if(j+winseq-1<row) {
//			 if (board[i][j]==player) {
//				 if(board[i+1][j+1]==player) {
//					 if(board[i+2][j+2]==player) {
//						 return true;
//					 }
//				 }
//			 }
				 for(int k=0;k<winseq;k++) {
					 if(board[i+k][j+k]==player) {
						 co++;
						 if(co==winseq) {
							 return true;
						 }
					 }
					 else {
						 co=0;
					 }
				 }
			 }
		 }
	 }
	return false;
 }
 public static boolean diagtwowin(int row, int col) {  		//Function to check win in the positive slope diagonal
	 for(int i=0;i<row;i++) {
		 for(int j=0; j<col; j++) {
			 if(j-winseq+1>-1) {
//			 if (board[i][j]==player) {
//				 if(board[i+1][j-1]==player) {
//					 if(board[i+2][j-2]==player) {
//						 return true;
//					 }
//				 }
//			 }
				 for(int k=0;k<winseq;k++) {
					 if(board[i+k][j-k]==player) {
						 co++;
						 if(co==winseq) {
							 return true;
						 }
					 }
					 else {
						 co=0;
					 }
				 }
			 }
		 }
	 }
	return false;
 }
 public static boolean checktie(int row, int col) {			//Function to check if there's a tie
	 for(int i=0;i<row;i++) {
		 for(int j=0;j<col;j++) {
			 if(board[i][j]==' ') {
				 return false;
			 }
		 }
	 }
	 return true;
 }
 public static void makefile(String filename) {					//Function to make a file
	try{
		savefile = new Formatter(filename+".txt");
	}
	catch(Exception e) {
		System.out.println("There was an error");
	}
 }
 public static void writefile(int row, int col) {			//Function to write a file
	 
	 savefile.format("%c",(char)row);
	 savefile.format("%c",(char)col);
	 savefile.format("%c",(char)nplayers);
	 savefile.format("%c",(char)winseq);
	 for(int i=0;i<row;i++) {
				 for(int j=0;j<col;j++) {
			 savefile.format("%c", board[i][j]);
		 }
			 }
 }
 public static void closefile() {					//To close file
	 savefile.close();
 }
 public static int opensavedfile(String filename) {			//Function to open a saved game file
	 infile=new File(filename+".txt");
	 try {
		 fr=new FileReader(infile);
		 int ch;
		 ch = fr.read();
		 row=ch;
		 ch = fr.read();
		 col=ch;
		 ch= fr.read();
		 nplayers=ch;
		 ch= fr.read();
		 winseq=ch;
		 for(int i=0;i<row;i++) {
			 for(int j=0;j<col;j++) {
				 if(ch!=-1) {
				 ch = fr.read();
				 board[i][j]=(char)ch;
				
				 }
			 }
		 }
		 count=2;
		 player=90;
		 System.out.println("Win sequence is: "+winseq);
		 
	 }
	 catch(Exception e) {
		 System.out.println("File not found.");
		 return 1;
	 }
	 return 0;
 }
 public static void promptwinsequence() {
	 System.out.println("What would be the win sequence?");
	 winseq=bol.nextInt();
	 if (winseq<3) {
		 System.out.println("The win sequence can't be less than 3.");
		 promptwinsequence();
	 }
 }
 public static void promptplayers() {
	 System.out.println("How many number of players?");
	 nplayers=bol.nextInt();
	 if (row*col/2<nplayers) {
		 System.out.println("Can't be these many players in such a small board.");
		 promptplayers();
	 }
 }
public static void promptrowcol() {
	 System.out.println("How many number of rows and columns?");
	 row=bol.nextInt();
	 col=bol.nextInt();
	 if(row<3||col<3) {
		 System.out.println("Rows or columns can't be less than 3 or negative.");
		 promptrowcol();
	 }
}
public static void playerchance() {
	 System.out.println("Player "+player+" enter row and column (numbers only separated by a space)");
	 r=bol.nextInt();
	 c=bol.nextInt();
	
}
}