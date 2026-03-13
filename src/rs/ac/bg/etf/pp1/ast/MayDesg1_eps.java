// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class MayDesg1_eps extends MayDesg1 {

    public MayDesg1_eps () {
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
        buffer.append("MayDesg1_eps(\n");

        buffer.append(tab);
        buffer.append(") [MayDesg1_eps]");
        return buffer.toString();
    }
}
