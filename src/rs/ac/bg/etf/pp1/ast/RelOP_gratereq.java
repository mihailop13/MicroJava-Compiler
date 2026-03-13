// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class RelOP_gratereq extends Relop {

    public RelOP_gratereq () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("RelOP_gratereq(\n");

        buffer.append(tab);
        buffer.append(") [RelOP_gratereq]");
        return buffer.toString();
    }
}
