// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Statement_print2 extends Statement {

    private Expr Expr;
    private ConstantNUM ConstantNUM;

    public Statement_print2 (Expr Expr, ConstantNUM ConstantNUM) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.ConstantNUM=ConstantNUM;
        if(ConstantNUM!=null) ConstantNUM.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public ConstantNUM getConstantNUM() {
        return ConstantNUM;
    }

    public void setConstantNUM(ConstantNUM ConstantNUM) {
        this.ConstantNUM=ConstantNUM;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(ConstantNUM!=null) ConstantNUM.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(ConstantNUM!=null) ConstantNUM.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(ConstantNUM!=null) ConstantNUM.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_print2(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstantNUM!=null)
            buffer.append(ConstantNUM.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_print2]");
        return buffer.toString();
    }
}
