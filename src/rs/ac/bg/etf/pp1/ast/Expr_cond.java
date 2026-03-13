// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Expr_cond extends Expr {

    private Condition Condition;
    private TernaniTrue TernaniTrue;
    private TernaniFalse TernaniFalse;

    public Expr_cond (Condition Condition, TernaniTrue TernaniTrue, TernaniFalse TernaniFalse) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.TernaniTrue=TernaniTrue;
        if(TernaniTrue!=null) TernaniTrue.setParent(this);
        this.TernaniFalse=TernaniFalse;
        if(TernaniFalse!=null) TernaniFalse.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public TernaniTrue getTernaniTrue() {
        return TernaniTrue;
    }

    public void setTernaniTrue(TernaniTrue TernaniTrue) {
        this.TernaniTrue=TernaniTrue;
    }

    public TernaniFalse getTernaniFalse() {
        return TernaniFalse;
    }

    public void setTernaniFalse(TernaniFalse TernaniFalse) {
        this.TernaniFalse=TernaniFalse;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(TernaniTrue!=null) TernaniTrue.accept(visitor);
        if(TernaniFalse!=null) TernaniFalse.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(TernaniTrue!=null) TernaniTrue.traverseTopDown(visitor);
        if(TernaniFalse!=null) TernaniFalse.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(TernaniTrue!=null) TernaniTrue.traverseBottomUp(visitor);
        if(TernaniFalse!=null) TernaniFalse.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr_cond(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaniTrue!=null)
            buffer.append(TernaniTrue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaniFalse!=null)
            buffer.append(TernaniFalse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr_cond]");
        return buffer.toString();
    }
}
