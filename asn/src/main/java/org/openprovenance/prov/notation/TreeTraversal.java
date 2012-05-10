package org.openprovenance.prov.notation;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Hashtable;

import  org.antlr.runtime.CommonTokenStream;
import  org.antlr.runtime.ANTLRFileStream;
import  org.antlr.runtime.CharStream;
import  org.antlr.runtime.Token;
import  org.antlr.runtime.tree.Tree;
import  org.antlr.runtime.tree.CommonTree;
import  org.antlr.runtime.tree.CommonTreeAdaptor;
import  org.antlr.runtime.tree.TreeAdaptor;

/* Class to traverse the syntax tree generated by antl parser, invoking constructor methods in TreeConstructor interface. */

public class TreeTraversal {

    private TreeConstructor c;
    public TreeTraversal(TreeConstructor c) {
        this.c=c;
    }

    public String getTokenString(Tree t) {
        if (t==null) return null;
        if (((CommonTree)t).getToken()==null) return null;
        return ((CommonTree)t).getToken().getText();
    }

    public String convertToken(String token) {
        return token;
    }

    public int convertInt(String token) {
        return Integer.valueOf(token);
    }
    
    public Object convert(Tree ast) {
        switch(ast.getType()) {

            /* Component 1 */

        case PROV_NParser.ENTITY:
            Object id=convert(ast.getChild(0));
            Object eAttrs=convert(ast.getChild(1));
            return c.convertEntity(id,eAttrs);

        case PROV_NParser.ACTIVITY:
            id=convert(ast.getChild(0));
            Object startTime=convert(ast.getChild(1));
            Object endTime=convert(ast.getChild(2));
            Object aAttrs=convert(ast.getChild(3));
            return c.convertActivity(id,startTime,endTime,aAttrs);

        case PROV_NParser.START:
            return c.convertStart(convertToken(getTokenString(ast.getChild(0))));

        case PROV_NParser.END:
            return c.convertEnd(convertToken(getTokenString  (ast.getChild(0))));

        case PROV_NParser.USED:
            Tree uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            Object uid=convert(uidTree);
            Object id2=convert(ast.getChild(1));
            Object id1=convert(ast.getChild(2));
            Object time=convert(ast.getChild(3));
            Object rAttrs=convert(ast.getChild(4));
            return c.convertUsed(uid, id2,id1,time,rAttrs);

        case PROV_NParser.WGB:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            time=convert(ast.getChild(3));
            rAttrs=convert(ast.getChild(4));
            return c.convertWasGeneratedBy(uid,id2,id1,time,rAttrs);

        case PROV_NParser.WSB:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            time=convert(ast.getChild(3));
            rAttrs=convert(ast.getChild(4));
            return c.convertWasStartedBy(uid,id2,id1,time,rAttrs);


        case PROV_NParser.WEB:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            time=convert(ast.getChild(3));
            rAttrs=convert(ast.getChild(4));
            return c.convertWasEndedBy(uid,id2,id1,time,rAttrs);

        case PROV_NParser.TIME:
            if (ast.getChildCount()==0) return null;
            if (ast.getChild(0)==null) return null;
            return getTokenString(ast.getChild(0));

        case PROV_NParser.WINVB:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            time=convert(ast.getChild(3));
            rAttrs=convert(ast.getChild(4));
            return c.convertWasInvalidatedBy(uid,id2,id1,time,rAttrs);



        case PROV_NParser.WIB:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            rAttrs=convert(ast.getChild(3));
            return c.convertWasInformedBy(uid,id2,id1,rAttrs);

        case PROV_NParser.WSBA:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            rAttrs=convert(ast.getChild(3));
            return c.convertWasStartedByActivity(uid,id2,id1,rAttrs);


            /* Component 2 */

        case PROV_NParser.AGENT:
            id=convert(ast.getChild(0));
            Object agAttrs=convert(ast.getChild(1));
            return c.convertAgent(id,agAttrs);

        case PROV_NParser.PLAN:
            if (ast.getChildCount()==0) {
                return null;
            } else {
                return convert(ast.getChild(0));
            }

        case PROV_NParser.WAT:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            rAttrs=convert(ast.getChild(3));
            return c.convertWasAttributedTo(uid,id2,id1,rAttrs);


        case PROV_NParser.WAW:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=(ast.getChild(2)==null)?null : convert(ast.getChild(2));
            Object pl=((ast.getChild(3)==null)?null : convert(ast.getChild(3)));
            rAttrs=convert(ast.getChild(4));
            return c.convertWasAssociatedWith(uid,id2,id1,pl,rAttrs);


        case PROV_NParser.AOBO:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            Object a=((ast.getChild(3)==null)?null : convert(ast.getChild(3)));
            rAttrs=convert(ast.getChild(4));
            return c.convertActedOnBehalfOf(uid,id2,id1,a,rAttrs);


            /* Component 3 */

        case PROV_NParser.WDF:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            Object pe=convert(ast.getChild(3));
            Object q2=convert(ast.getChild(4));
            Object q1=convert(ast.getChild(5));
            Object dAttrs=convert(ast.getChild(6));
            return c.convertWasDerivedFrom(uid,id2,id1,pe,q2,q1,dAttrs);

        case PROV_NParser.WRO:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            pe=convert(ast.getChild(3));
            q2=convert(ast.getChild(4));
            q1=convert(ast.getChild(5));
            dAttrs=convert(ast.getChild(6));
            return c.convertWasRevisionOf(uid,id2,id1,pe,q2,q1,dAttrs);

        case PROV_NParser.WQF:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            pe=convert(ast.getChild(3));
            q2=convert(ast.getChild(4));
            q1=convert(ast.getChild(5));
            dAttrs=convert(ast.getChild(6));
            return c.convertWasQuotedFrom(uid,id2,id1,pe,q2,q1,dAttrs);

        case PROV_NParser.ORIGINALSOURCE:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            pe=convert(ast.getChild(3));
            q2=convert(ast.getChild(4));
            q1=convert(ast.getChild(5));
            dAttrs=convert(ast.getChild(6));
            return c.convertHadOriginalSource(uid,id2,id1,pe,q2,q1,dAttrs);

        case PROV_NParser.TRACEDTO:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            dAttrs=convert(ast.getChild(3));
            return c.convertTracedTo(uid,id2,id1,dAttrs);

            /* Component 4 */

        case PROV_NParser.ALTERNATE:
            id2=convert(ast.getChild(0));
            id1=convert(ast.getChild(1));
            return c.convertAlternateOf(id2,id1);

        case PROV_NParser.SPECIALIZATION:
            id2=convert(ast.getChild(0));
            id1=convert(ast.getChild(1));
            return c.convertSpecializationOf(id2,id1);

            /* Component 5 */

        case PROV_NParser.DBIF:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            Object keymap=convert(ast.getChild(3));
            dAttrs=convert(ast.getChild(4));
            return c.convertInsertion(uid,id2,id1,keymap,dAttrs);


        case PROV_NParser.DBRF:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            id1=convert(ast.getChild(2));
            Object keyset=convert(ast.getChild(3));
	    Object keylist=c.convertKeys((List)keyset);
            dAttrs=convert(ast.getChild(4));
            return c.convertRemoval(uid,id2,id1,keylist,dAttrs);

        case PROV_NParser.MEM:
            uidTree=ast.getChild(0);
            if (uidTree.getChildCount()>0) {
                uidTree=uidTree.getChild(0);
            }
            uid=convert(uidTree);
            id2=convert(ast.getChild(1));
            keymap=convert(ast.getChild(2));
            Object complete=convert(ast.getChild(3));
            dAttrs=convert(ast.getChild(4));
            return c.convertMemberOf(uid,id2,keymap,complete,dAttrs);


        case PROV_NParser.KEYS:
            List<Object> keys=new LinkedList();
            for (int i=0; i< ast.getChildCount(); i++) {
                Object o=convert(ast.getChild(i));
                keys.add(o);
            }
            return keys;


        case PROV_NParser.VALUES:
            List<Object> values=new LinkedList();
            for (int i=0; i< ast.getChildCount(); i++) {
                Object o=convert(ast.getChild(i));
                values.add(o);
            }
            return values;

        case PROV_NParser.KES:
            Object keys1=convert(ast.getChild(0));
            Object entities=convert(ast.getChild(1));

	    keys=(List)keys1;
	    values=(List)entities;
	    List entries=new LinkedList();
	    int ii=0;
	    for (Object key : keys) {
		Object value=values.get(ii);
		entries.add(c.convertEntry(key,value));
		ii++;
	    }

            return c.convertKeyEntitySet(entries);


            /* Component 6 */

        case PROV_NParser.NOTE:
            id=convert(ast.getChild(0));
            Object nAttrs=convert(ast.getChild(1));
            return c.convertNote(id,nAttrs);


        case PROV_NParser.HAN:
            id2=convert(ast.getChild(0));
            id1=convert(ast.getChild(1));
            return c.convertHasAnnotation(id2,id1);

            /* Miscellaneous Constructs */

        case PROV_NParser.EXPRESSIONS:
        case PROV_NParser.BUNDLES:
            List<Object> records=new LinkedList();
            for (int i=0; i< ast.getChildCount(); i++) {
                Object o=convert(ast.getChild(i));
                records.add(o);
            }
            return records;

        case PROV_NParser.BUNDLE:
            Object nss=convert(ast.getChild(0));
	    records=(List<Object>)convert(ast.getChild(1));
	    List<Object> bundles=null;
	    if (ast.getChild(2)!=null)
		bundles=(List<Object>)convert(ast.getChild(2));
            return c.convertBundle(nss,records, bundles);

        case PROV_NParser.NAMEDBUNDLE:
            id=convert(ast.getChild(0));
            nss=convert(ast.getChild(1));
	    records=null;
	    if (ast.getChild(2)!=null)
		records=(List<Object>)convert(ast.getChild(2));
            return c.convertNamedBundle(id,nss,records);

        case PROV_NParser.ATTRIBUTES:
            List<Object> attributes=new LinkedList();
            for (int i=0; i< ast.getChildCount(); i++) {
                attributes.add(convert(ast.getChild(i)));
            }
            return c.convertAttributes(attributes);

        case PROV_NParser.ID:
            return c.convertId(convertToken(getTokenString(ast.getChild(0))));

        case PROV_NParser.ATTRIBUTE:
            Object attr1=convertToken(getTokenString(ast.getChild(0)));
            Object val1=convert(ast.getChild(1));

            return c.convertAttribute(attr1,val1);

        case PROV_NParser.STRING:
            return c.convertString(convertToken(getTokenString(ast.getChild(0))));

        case PROV_NParser.INT:
            return c.convertInt(convertInt(getTokenString(ast.getChild(0))));
        
        case PROV_NParser.QNAM:
            return c.convertQualifiedName(convertToken(getTokenString(ast.getChild(0))));

        case PROV_NParser.IRI:
            String iri=convertToken(getTokenString(ast.getChild(0)));
            return c.convertIRI(iri);

        case PROV_NParser.TYPEDLITERAL:
            String v1=convertToken(getTokenString(ast.getChild(0)));
            String v2;
            
            if (ast.getChild(1)==null) {
                v2="xsd:QName";
                v1="\"" + v1 + "\"";
            } else {
                v2=(String)convert(ast.getChild(1));
            }

            return c.convertTypedLiteral(v2,v1);

        case PROV_NParser.NAMESPACE:
            Object pre=convert(ast.getChild(0));
            Object iri1=getTokenString(ast.getChild(1));
            return c.convertNamespace(pre,iri1);

        case PROV_NParser.DEFAULTNAMESPACE:
            iri1=getTokenString(ast.getChild(0));
            return c.convertDefaultNamespace(iri1);

        case PROV_NParser.PREFIX:
            String prefix=getTokenString(ast.getChild(0));
            return c.convertPrefix(prefix);

        case PROV_NParser.NAMESPACES:
            List<Object> namespaces=new LinkedList();
            for (int i=0; i< ast.getChildCount(); i++) {
                Object o=convert(ast.getChild(i));
                namespaces.add(o);
            }
            return c.convertNamespaces(namespaces);


        case PROV_NParser.TRUE:
            return true;
        case PROV_NParser.FALSE:
            return false;
        case PROV_NParser.UNKNOWN:
            return null;

        }


        return null;

    }


}

