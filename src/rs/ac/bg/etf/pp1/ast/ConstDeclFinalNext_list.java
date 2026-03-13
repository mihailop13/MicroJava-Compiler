// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclFinalNext_list extends ConstDeclFinalNext {

    private ConstDeclFinal ConstDeclFinal;
    private ConstDeclFinalNext ConstDeclFinalNext;

    public ConstDeclFinalNext_list (ConstDeclFinal ConstDeclFinal, ConstDeclFinalNext ConstDeclFinalNext) {
        this.ConstDeclFinal=ConstDeclFinal;
        if(ConstDeclFinal!=null) ConstDeclFinal.setParent(this);
        this.ConstDeclFinalNext=ConstDeclFinalNext;
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.setParent(this);
    }

    public ConstDeclFinal getConstDeclFinal() {
        return ConstDeclFinal;
    }

    public void setConstDeclFinal(ConstDeclFinal ConstDeclFinal) {
        this.ConstDeclFinal=ConstDeclFinal;
    }

    public ConstDeclFinalNext getConstDeclFinalNext() {
        return ConstDeclFinalNext;
    }

    public void setConstDeclFinalNext(ConstDeclFinalNext ConstDeclFinalNext) {
        this.ConstDeclFinalNext=ConstDeclFinalNext;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclFinal!=null) ConstDeclFinal.accept(visitor);
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclFinal!=null) ConstDeclFinal.traverseTopDown(visitor);
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclFinal!=null) ConstDeclFinal.traverseBottomUp(visitor);
        if(ConstDeclFinalNext!=null) ConstDeclFinalNext.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclFinalNext_list(\n");

        if(ConstDeclFinal!=null)
            buffer.append(ConstDeclFinal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclFinalNext!=null)
            buffer.append(ConstDeclFinalNext.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclFinalNext_list]");
        return buffer.toString();
    }
}
