package org.openprovenance.prov.template;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.openprovenance.prov.model.ActedOnBehalfOf;
import org.openprovenance.prov.model.Activity;
import org.openprovenance.prov.model.Agent;
import org.openprovenance.prov.model.AlternateOf;
import org.openprovenance.prov.model.DerivedByInsertionFrom;
import org.openprovenance.prov.model.DerivedByRemovalFrom;
import org.openprovenance.prov.model.DictionaryMembership;
import org.openprovenance.prov.model.Entity;
import org.openprovenance.prov.model.HadMember;
import org.openprovenance.prov.model.HasOther;
import org.openprovenance.prov.model.MentionOf;
import org.openprovenance.prov.model.NamedBundle;
import org.openprovenance.prov.model.ProvFactory;
import org.openprovenance.prov.model.ProvUtilities;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.SpecializationOf;
import org.openprovenance.prov.model.Statement;
import org.openprovenance.prov.model.StatementAction;
import org.openprovenance.prov.model.StatementOrBundle;
import org.openprovenance.prov.model.Used;
import org.openprovenance.prov.model.WasAssociatedWith;
import org.openprovenance.prov.model.WasAttributedTo;
import org.openprovenance.prov.model.WasDerivedFrom;
import org.openprovenance.prov.model.WasEndedBy;
import org.openprovenance.prov.model.WasGeneratedBy;
import org.openprovenance.prov.model.WasInfluencedBy;
import org.openprovenance.prov.model.WasInformedBy;
import org.openprovenance.prov.model.WasInvalidatedBy;
import org.openprovenance.prov.model.WasStartedBy;

import static org.openprovenance.prov.template.Expand.APP_NS;

public class ExpandAction implements StatementAction {
    
    final private ProvFactory pf;
    final private Expand expand;
    final private Hashtable<QualifiedName, QualifiedName> env;
    final private ProvUtilities u;
    final private List<StatementOrBundle> ll=new LinkedList<StatementOrBundle>();
    final private List<Integer> index;
    final private Bindings bindings;
    final private Groupings grp1;

    public ExpandAction(ProvFactory pf, ProvUtilities u, Expand expand, Hashtable<QualifiedName, QualifiedName> env, List<Integer> index, Bindings bindings1, Groupings grp1) {
	this.pf=pf;
	this.expand=expand;
	this.env=env;
	this.u=u;
	this.index=index;
	this.bindings=bindings1;
	this.grp1=grp1;
    }

    @Override
    public void doAction(Activity s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(Used s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasStartedBy s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(Agent e) {
	Agent res=pf.newAgent(e);
	QualifiedName id=res.getId();
	boolean updated=setExpand(res, id, 0);	
	ll.add(res);
	if (updated) addOrderAttribute(res);	
    }

    @Override
    public void doAction(AlternateOf s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasAssociatedWith s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasAttributedTo s) {
	WasAttributedTo res=pf.newWasAttributedTo(s);
	QualifiedName id=res.getId();
	boolean updated1=setExpand(res, id, 0);	
	QualifiedName en=res.getEntity();
	boolean updated2=setExpand(res, en, 1);	
	QualifiedName ag=res.getAgent();
	boolean updated3=setExpand(res, ag, 2);	
	boolean updated=updated1 || updated2 || updated3;
	ll.add(res);
	if (updated) addOrderAttribute(res);	
    }

    @Override
    public void doAction(WasInfluencedBy s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(ActedOnBehalfOf s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasDerivedFrom s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(DictionaryMembership s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(DerivedByRemovalFrom s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasEndedBy s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(Entity e) {
	Entity res=pf.newEntity(e);
	QualifiedName id=res.getId();
	boolean updated=setExpand(res, id, 0);	
	ll.add(res);
	if (updated) addOrderAttribute(res);
    }

    private void addOrderAttribute(HasOther res) {
	res.getOther().add(pf.newOther(APP_NS, "order", "app", index, pf.getName().XSD_STRING));
    }

    private boolean setExpand(Statement res, QualifiedName id, int position) {
	if (expand.isVariable(id)) {
	    QualifiedName val=env.get(id);
	    if (val!=null) {
		u.setter(res, position, val);
		return true;
	    }
	}
	return false;
    }

    @Override
    public void doAction(WasGeneratedBy s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasInvalidatedBy s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(HadMember s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(MentionOf s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(SpecializationOf s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(DerivedByInsertionFrom s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(WasInformedBy s) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void doAction(NamedBundle bun, ProvUtilities provUtilities) {
	List<Statement> statements=bun.getStatement();
	List<Statement> newStatements=new LinkedList<Statement>();
		
	for (Statement s: statements) {
	    for (StatementOrBundle sb: expand.expand(s, bindings, grp1)) {
		newStatements.add((Statement)sb);
	    }
	    
	}
	ll.add(pf.newNamedBundle(bun.getId(), newStatements));
	
    }

    public List<StatementOrBundle> getList() {
	return ll;
    }

}