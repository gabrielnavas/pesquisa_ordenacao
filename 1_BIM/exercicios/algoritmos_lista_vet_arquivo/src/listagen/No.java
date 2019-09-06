package listagen;

public class No extends NoGen
{
    private NoGen head;
    private NoGen tail;

    public No() {
        head = null;
        tail = null;
    }

    public No(NoGen head, NoGen tail) {
        this.head = head;
        this.tail = tail;
    }

    public NoGen getHead() {
        return head;
    }

    public void setHead(NoGen head) {
        this.head = head;
    }

    public NoGen getTail() {
        return tail;
    }

    public void setTail(NoGen tail) {
        this.tail = tail;
    }
}
