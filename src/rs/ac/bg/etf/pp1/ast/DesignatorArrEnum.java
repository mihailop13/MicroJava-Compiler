// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArrEnum extends Designator {

    private DesignatorName DesignatorName;
    private DesignatorKind DesignatorKind;

    public DesignatorArrEnum (DesignatorName DesignatorName, DesignatorKind DesignatorKind) {
        this.DesignatorName=DesignatorName;
        if(DesignatorName!=null) DesignatorName.setParent(this);
        this.DesignatorKind=DesignatorKind;
        if(DesignatorKind!=null) DesignatorKind.setParent(this);
    }

    public DesignatorName getDesignatorName() {
        return DesignatorName;
    }

    public void setDesignatorName(DesignatorName DesignatorName) {
        this.DesignatorName=DesignatorName;
    }

    public DesignatorKind getDesignatorKind() {
        return DesignatorKind;
    }

    public void setDesignatorKind(DesignatorKind DesignatorKind) {
        this.DesignatorKind=DesignatorKind;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.accept(visitor);
        if(DesignatorKind!=null) DesignatorKind.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorName!=null) DesignatorName.traverseTopDown(visitor);
        if(DesignatorKind!=null) DesignatorKind.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorName!=null) DesignatorName.traverseBottomUp(visitor);
        if(DesignatorKind!=null) DesignatorKind.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArrEnum(\n");

        if(DesignatorName!=null)
            buffer.append(DesignatorName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorKind!=null)
            buffer.append(DesignatorKind.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArrEnum]");
        return buffer.toString();
    }
}
