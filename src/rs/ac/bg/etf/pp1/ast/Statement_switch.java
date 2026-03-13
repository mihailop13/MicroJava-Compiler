// generated with ast extension for cup
// version 0.8
// 6/2/2026 22:45:30


package rs.ac.bg.etf.pp1.ast;

public class Statement_switch extends Statement {

    private NonTerminalSwitch NonTerminalSwitch;
    private SwitchExpr SwitchExpr;
    private Cases Cases;

    public Statement_switch (NonTerminalSwitch NonTerminalSwitch, SwitchExpr SwitchExpr, Cases Cases) {
        this.NonTerminalSwitch=NonTerminalSwitch;
        if(NonTerminalSwitch!=null) NonTerminalSwitch.setParent(this);
        this.SwitchExpr=SwitchExpr;
        if(SwitchExpr!=null) SwitchExpr.setParent(this);
        this.Cases=Cases;
        if(Cases!=null) Cases.setParent(this);
    }

    public NonTerminalSwitch getNonTerminalSwitch() {
        return NonTerminalSwitch;
    }

    public void setNonTerminalSwitch(NonTerminalSwitch NonTerminalSwitch) {
        this.NonTerminalSwitch=NonTerminalSwitch;
    }

    public SwitchExpr getSwitchExpr() {
        return SwitchExpr;
    }

    public void setSwitchExpr(SwitchExpr SwitchExpr) {
        this.SwitchExpr=SwitchExpr;
    }

    public Cases getCases() {
        return Cases;
    }

    public void setCases(Cases Cases) {
        this.Cases=Cases;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NonTerminalSwitch!=null) NonTerminalSwitch.accept(visitor);
        if(SwitchExpr!=null) SwitchExpr.accept(visitor);
        if(Cases!=null) Cases.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NonTerminalSwitch!=null) NonTerminalSwitch.traverseTopDown(visitor);
        if(SwitchExpr!=null) SwitchExpr.traverseTopDown(visitor);
        if(Cases!=null) Cases.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NonTerminalSwitch!=null) NonTerminalSwitch.traverseBottomUp(visitor);
        if(SwitchExpr!=null) SwitchExpr.traverseBottomUp(visitor);
        if(Cases!=null) Cases.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Statement_switch(\n");

        if(NonTerminalSwitch!=null)
            buffer.append(NonTerminalSwitch.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchExpr!=null)
            buffer.append(SwitchExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Cases!=null)
            buffer.append(Cases.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Statement_switch]");
        return buffer.toString();
    }
}
