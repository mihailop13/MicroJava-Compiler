// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class For_args implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MayDesg1 MayDesg1;
    private MayCond MayCond;
    private MayDesg2 MayDesg2;

    public For_args (MayDesg1 MayDesg1, MayCond MayCond, MayDesg2 MayDesg2) {
        this.MayDesg1=MayDesg1;
        if(MayDesg1!=null) MayDesg1.setParent(this);
        this.MayCond=MayCond;
        if(MayCond!=null) MayCond.setParent(this);
        this.MayDesg2=MayDesg2;
        if(MayDesg2!=null) MayDesg2.setParent(this);
    }

    public MayDesg1 getMayDesg1() {
        return MayDesg1;
    }

    public void setMayDesg1(MayDesg1 MayDesg1) {
        this.MayDesg1=MayDesg1;
    }

    public MayCond getMayCond() {
        return MayCond;
    }

    public void setMayCond(MayCond MayCond) {
        this.MayCond=MayCond;
    }

    public MayDesg2 getMayDesg2() {
        return MayDesg2;
    }

    public void setMayDesg2(MayDesg2 MayDesg2) {
        this.MayDesg2=MayDesg2;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MayDesg1!=null) MayDesg1.accept(visitor);
        if(MayCond!=null) MayCond.accept(visitor);
        if(MayDesg2!=null) MayDesg2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MayDesg1!=null) MayDesg1.traverseTopDown(visitor);
        if(MayCond!=null) MayCond.traverseTopDown(visitor);
        if(MayDesg2!=null) MayDesg2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MayDesg1!=null) MayDesg1.traverseBottomUp(visitor);
        if(MayCond!=null) MayCond.traverseBottomUp(visitor);
        if(MayDesg2!=null) MayDesg2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("For_args(\n");

        if(MayDesg1!=null)
            buffer.append(MayDesg1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MayCond!=null)
            buffer.append(MayCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MayDesg2!=null)
            buffer.append(MayDesg2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [For_args]");
        return buffer.toString();
    }
}
