package ensta.model;

public class TestBoard {
    public static void main(String args[]) {
        Board board1 = new Board("board1");
        Board board2 = new Board("board2", 5);
        Board board3 = new Board("board3", 30);

        board1.print();
        board2.print();
        board3.print();
    }
}
