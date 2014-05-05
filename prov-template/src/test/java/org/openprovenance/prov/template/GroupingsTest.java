package org.openprovenance.prov.template;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.openprovenance.prov.interop.InteropFramework;
import org.openprovenance.prov.model.Document;
import org.openprovenance.prov.model.NamedBundle;
import org.openprovenance.prov.model.Namespace;
import org.openprovenance.prov.model.ProvFactory;
import org.openprovenance.prov.model.QualifiedName;
import org.openprovenance.prov.model.TypedValue;

import static org.openprovenance.prov.template.Expand.VAR_NS;
import static org.openprovenance.prov.template.Expand.APP_NS;
import junit.framework.TestCase;

public class GroupingsTest extends TestCase {

    static final String EX_NS = "http://example.org/";

    public GroupingsTest(String testName) {
	super(testName);
    }
    
    ProvFactory pf=new org.openprovenance.prov.xml.ProvFactory();
    QualifiedName var_a=pf.newQualifiedName(VAR_NS, "a", "var");
    QualifiedName var_b=pf.newQualifiedName(VAR_NS, "b", "var");
    QualifiedName var_c=pf.newQualifiedName(VAR_NS, "c", "var");
    QualifiedName var_d=pf.newQualifiedName(VAR_NS, "d", "var");
 
    




    public Groupings getGroupings(String filename) throws IOException, JAXBException, Throwable {
        Document doc=(Document) new InteropFramework().loadProvUnknownGraph(filename);
        
        Groupings grps=Groupings.fromDocument(doc);

        System.out.println("Groupings is " + grps);
        
        
        return grps;
    }
    
    

    public void testGroupings1() throws IOException, JAXBException, Throwable {
	Groupings grps=getGroupings("src/test/resources/template20.provn");
	assertTrue(grps.size()==2);
    }

    public void testGroupings2() throws IOException, JAXBException, Throwable {
	Groupings grps=getGroupings("src/test/resources/template21.provn");
	assertTrue(grps.size()==1);
    }


}
