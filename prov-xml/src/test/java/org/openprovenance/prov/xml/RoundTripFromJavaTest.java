package org.openprovenance.prov.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Hashtable;
import java.net.URI;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.xml.sax.SAXException;

import junit.framework.TestCase;

/**
 * Unit test for simple Provenance Challenge 1 like workflow.
 */
public class RoundTripFromJavaTest extends TestCase {

    public static final String EX_NS = "http://example.org/";
    public static final String EX_PREFIX = "ex";
     
    static final ProvUtilities util=new ProvUtilities();


    static final Hashtable<String, String> namespaces;

    public static ProvFactory pFactory;
    
    static Hashtable<String, String> updateNamespaces (Hashtable<String, String> nss) {
	nss.put(EX_PREFIX, EX_NS);
	nss.put("xml", "http://www.w3.org/XML/1998/namespace");
	return nss;
    }
    
    
    static {
	namespaces = updateNamespaces(new Hashtable<String, String>());
	pFactory = new ProvFactory(namespaces);
    }

    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public RoundTripFromJavaTest(String testName) {
	super(testName);
    }

    public boolean urlFlag = true;

    /**
     * @return the suite of tests being tested
     */

    void updateNamespaces(Document doc) {
	Hashtable<String, String> nss = new Hashtable<String, String>();
	updateNamespaces(nss);
	doc.setNss(nss);
    }
   
    
    public void makeDocAndTest(Statement stment, String file) throws JAXBException {
	Document doc = pFactory.newDocument();
	doc.getEntityOrActivityOrWasGeneratedBy().add(stment);
	updateNamespaces(doc);

	writeXMLDocument(doc, file);
	
	Document doc2=readXMLDocument(file);
	assertTrue("self doc differ", doc.equals(doc));
	assertTrue("self doc2 differ", doc2.equals(doc2));
	assertTrue("doc differs doc2", doc.equals(doc2));
    }

    public Document readXMLDocument(String file) throws javax.xml.bind.JAXBException {

	ProvDeserialiser deserial = ProvDeserialiser
	        .getThreadProvDeserialiser();
	Document c = deserial.deserialiseDocument(new File(file));
	return c;
    }
    
    public void writeXMLDocument(Document doc, String file) throws JAXBException {
	ProvSerialiser serial = ProvSerialiser.getThreadProvSerialiser();
	serial.serialiseDocument(new File(file), doc, true);
    }
    
    ///////////////////////////////////////////////////////////////////////
    
    
    public void testEntity1() throws JAXBException  {
	Entity a = pFactory.newEntity("ex:e1");
	makeDocAndTest(a,"target/entity1.xml");
    }
    public void testEntity2() throws JAXBException  {
   	Entity a = pFactory.newEntity("ex:e2", "entity2");
   	makeDocAndTest(a,"target/entity2.xml");
    }

    
    public void testEntity3() throws JAXBException  {
   	Entity a = pFactory.newEntity("ex:e2", "entity2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	makeDocAndTest(a,"target/entity3.xml");
    }
    public void testEntity4() throws JAXBException  {
   	Entity a = pFactory.newEntity("ex:e2", "entity2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
   	makeDocAndTest(a,"target/entity4.xml");
    }
    public void testEntity5() throws JAXBException  {
   	Entity a = pFactory.newEntity("ex:e2", "entity2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
   	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	makeDocAndTest(a,"target/entity5.xml");
    }
   
    
    public void testEntity6() throws JAXBException  {
   	Entity a = pFactory.newEntity("ex:e6", "entity6");
   	a.getType().add("a");
   	a.getType().add(1);
   	a.getType().add(1.0);
   	a.getType().add(true);
   	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
   	a.getType().add(pFactory.newTimeNow());
   	URIWrapper w=new URIWrapper();
   	w.setValue(URI.create(EX_NS+"hello"));
   	a.getType().add(w);
   	makeDocAndTest(a,"target/entity6.xml");
    }

    public void testEntity7() throws JAXBException  {
       	Entity a = pFactory.newEntity("ex:e7", "entity7");
       	pFactory.addType(a,"a");
       	pFactory.addType(a,1);
       	pFactory.addType(a,1.0);
       	pFactory.addType(a,true);
       	pFactory.addType(a,new QName(EX_NS, "abc", EX_PREFIX));
       	pFactory.addType(a,pFactory.newTimeNow());
       	pFactory.addType(a,URI.create(EX_NS+"hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	a.getLocation().add("London");
   	a.getLocation().add(1);
   	a.getLocation().add(1.0);
   	a.getLocation().add(true);
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(pFactory.newTimeNow());
   	URIWrapper w=new URIWrapper();
   	w.setValue(URI.create(EX_NS+"london"));
   	a.getLocation().add(w);
       	makeDocAndTest(a,"target/entity7.xml");
    }
    public void testEntity8() throws JAXBException  {
       	Entity a = pFactory.newEntity("ex:e8", "entity8");
       	a.getType().add("a");
       	a.getType().add("a");
       	a.getType().add(1);
       	a.getType().add(1);
       	a.getType().add(1.0);
       	a.getType().add(1.0);
       	a.getType().add(true);
       	a.getType().add(true);
       	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
       	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
       	a.getType().add(pFactory.newTimeNow());
       	a.getType().add(pFactory.newTimeNow());
       	URIWrapper w=new URIWrapper();
       	w.setValue(URI.create(EX_NS+"hello"));
       	a.getType().add(w);
       	a.getType().add(w);
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	a.getLocation().add("London");
   	a.getLocation().add("London");
   	a.getLocation().add(1);
   	a.getLocation().add(1);
   	a.getLocation().add(1.0);
   	a.getLocation().add(1.0);
   	a.getLocation().add(true);
   	a.getLocation().add(true);
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(pFactory.newTimeNow());
   	a.getLocation().add(pFactory.newTimeNow());
   	URIWrapper w2=new URIWrapper();
   	w2.setValue(URI.create(EX_NS+"london"));
   	a.getLocation().add(w2);
   	a.getLocation().add(w2);

       	makeDocAndTest(a,"target/entity8.xml");
    }

    ///////////////////////////////////////////////////////////////////////
    
    
    public void testActivity1() throws JAXBException  {
	Activity a = pFactory.newActivity("ex:a1");
	makeDocAndTest(a,"target/activity1.xml");
    }
    public void testActivity2() throws JAXBException  {
   	Activity a = pFactory.newActivity("ex:a2", "activity2");
   	makeDocAndTest(a,"target/activity2.xml");
    }

    public void testActivity3() throws JAXBException  {
	Activity a = pFactory.newActivity("ex:a1");
	a.setStartTime(pFactory.newTimeNow());
	a.setEndTime(pFactory.newTimeNow());
	makeDocAndTest(a,"target/activity3.xml");
    }

    public void testActivity4() throws JAXBException  {
   	Activity a = pFactory.newActivity("ex:a2", "activity2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
   	makeDocAndTest(a,"target/activity4.xml");
    }
    public void testActivity5() throws JAXBException  {
   	Activity a = pFactory.newActivity("ex:a2", "activity2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
   	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	makeDocAndTest(a,"target/activity5.xml");
    }
   
    
    public void testActivity6() throws JAXBException  {
   	Activity a = pFactory.newActivity("ex:a6", "activity6");
   	a.getType().add("a");
   	a.getType().add(1);
   	a.getType().add(1.0);
   	a.getType().add(true);
   	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
   	a.getType().add(pFactory.newTimeNow());
   	URIWrapper w=new URIWrapper();
   	w.setValue(URI.create(EX_NS+"hello"));
   	a.getType().add(w);
   	makeDocAndTest(a,"target/activity6.xml");
    }

    public void testActivity7() throws JAXBException  {
       	Activity a = pFactory.newActivity("ex:a7", "activity7");
       	pFactory.addType(a,"a");
       	pFactory.addType(a,1);
       	pFactory.addType(a,1.0);
       	pFactory.addType(a,true);
       	pFactory.addType(a,new QName(EX_NS, "abc", EX_PREFIX));
       	pFactory.addType(a,pFactory.newTimeNow());
       	pFactory.addType(a,URI.create(EX_NS+"hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	a.getLocation().add("London");
   	a.getLocation().add(1);
   	a.getLocation().add(1.0);
   	a.getLocation().add(true);
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(pFactory.newTimeNow());
   	URIWrapper w=new URIWrapper();
   	w.setValue(URI.create(EX_NS+"london"));
   	a.getLocation().add(w);
       	makeDocAndTest(a,"target/activity7.xml");
    }
    public void testActivity8() throws JAXBException  {
       	Activity a = pFactory.newActivity("ex:a8", "activity8");
	a.setStartTime(pFactory.newTimeNow());
	a.setEndTime(pFactory.newTimeNow());
       	a.getType().add("a");
       	a.getType().add("a");
       	a.getType().add(1);
       	a.getType().add(1);
       	a.getType().add(1.0);
       	a.getType().add(1.0);
       	a.getType().add(true);
       	a.getType().add(true);
       	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
       	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
       	a.getType().add(pFactory.newTimeNow());
       	a.getType().add(pFactory.newTimeNow());
       	URIWrapper w=new URIWrapper();
       	w.setValue(URI.create(EX_NS+"hello"));
       	a.getType().add(w);
       	a.getType().add(w);
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	a.getLocation().add("London");
   	a.getLocation().add("London");
   	a.getLocation().add(1);
   	a.getLocation().add(1);
   	a.getLocation().add(1.0);
   	a.getLocation().add(1.0);
   	a.getLocation().add(true);
   	a.getLocation().add(true);
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(pFactory.newTimeNow());
   	a.getLocation().add(pFactory.newTimeNow());
   	URIWrapper w2=new URIWrapper();
   	w2.setValue(URI.create(EX_NS+"london"));
   	a.getLocation().add(w2);
   	a.getLocation().add(w2);

       	makeDocAndTest(a,"target/activity8.xml");
    }

    ///////////////////////////////////////////////////////////////////////
    
    
    public void testAgent1() throws JAXBException  {
	Agent a = pFactory.newAgent("ex:ag1");
	makeDocAndTest(a,"target/agent1.xml");
    }
    public void testAgent2() throws JAXBException  {
   	Agent a = pFactory.newAgent("ex:ag2", "agent2");
   	makeDocAndTest(a,"target/agent2.xml");
    }

    
    public void testAgent3() throws JAXBException  {
   	Agent a = pFactory.newAgent("ex:ag2", "agent2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	makeDocAndTest(a,"target/agent3.xml");
    }
    public void testAgent4() throws JAXBException  {
   	Agent a = pFactory.newAgent("ex:ag2", "agent2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
   	makeDocAndTest(a,"target/agent4.xml");
    }
    public void testAgent5() throws JAXBException  {
   	Agent a = pFactory.newAgent("ex:ag2", "agent2");
   	a.getLabel().add(pFactory.newInternationalizedString("hello"));
   	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
   	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	makeDocAndTest(a,"target/agent5.xml");
    }
   
    
    public void testAgent6() throws JAXBException  {
   	Agent a = pFactory.newAgent("ex:ag6", "agent6");
   	a.getType().add("a");
   	a.getType().add(1);
   	a.getType().add(1.0);
   	a.getType().add(true);
   	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
   	a.getType().add(pFactory.newTimeNow());
   	URIWrapper w=new URIWrapper();
   	w.setValue(URI.create(EX_NS+"hello"));
   	a.getType().add(w);
   	makeDocAndTest(a,"target/agent6.xml");
    }

    public void testAgent7() throws JAXBException  {
       	Agent a = pFactory.newAgent("ex:ag7", "agent7");
       	pFactory.addType(a,"a");
       	pFactory.addType(a,1);
       	pFactory.addType(a,1.0);
       	pFactory.addType(a,true);
       	pFactory.addType(a,new QName(EX_NS, "abc", EX_PREFIX));
       	pFactory.addType(a,pFactory.newTimeNow());
       	pFactory.addType(a,URI.create(EX_NS+"hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	a.getLocation().add("London");
   	a.getLocation().add(1);
   	a.getLocation().add(1.0);
   	a.getLocation().add(true);
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(pFactory.newTimeNow());
   	URIWrapper w=new URIWrapper();
   	w.setValue(URI.create(EX_NS+"london"));
   	a.getLocation().add(w);
       	makeDocAndTest(a,"target/agent7.xml");
    }
    public void testAgent8() throws JAXBException  {
       	Agent a = pFactory.newAgent("ex:ag8", "agent8");
       	a.getType().add("a");
       	a.getType().add("a");
       	a.getType().add(1);
       	a.getType().add(1);
       	a.getType().add(1.0);
       	a.getType().add(1.0);
       	a.getType().add(true);
       	a.getType().add(true);
       	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
       	a.getType().add(new QName(EX_NS, "abc", EX_PREFIX));
       	a.getType().add(pFactory.newTimeNow());
       	a.getType().add(pFactory.newTimeNow());
       	URIWrapper w=new URIWrapper();
       	w.setValue(URI.create(EX_NS+"hello"));
       	a.getType().add(w);
       	a.getType().add(w);
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("hello"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bye","EN"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
       	a.getLabel().add(pFactory.newInternationalizedString("bonjour","FR"));
   	a.getLocation().add("London");
   	a.getLocation().add("London");
   	a.getLocation().add(1);
   	a.getLocation().add(1);
   	a.getLocation().add(1.0);
   	a.getLocation().add(1.0);
   	a.getLocation().add(true);
   	a.getLocation().add(true);
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(new QName(EX_NS, "london", EX_PREFIX));
   	a.getLocation().add(pFactory.newTimeNow());
   	a.getLocation().add(pFactory.newTimeNow());
   	URIWrapper w2=new URIWrapper();
   	w2.setValue(URI.create(EX_NS+"london"));
   	a.getLocation().add(w2);
   	a.getLocation().add(w2);

       	makeDocAndTest(a,"target/agent8.xml");
    }


    ///////////////////////////////////////////////////////////////////////
    
    public QName q(String n) {
	return new QName(EX_NS, n, EX_PREFIX);
    }
    
    public void testGeneration1() throws JAXBException  {
	WasGeneratedBy gen = pFactory.newWasGeneratedBy(q("gen1"),
							pFactory.newEntityRef(q("e1")),
							null,
							null);
	makeDocAndTest(gen,"target/generation1.xml");
    }


    public void testGeneration2() throws JAXBException  {
	WasGeneratedBy gen = pFactory.newWasGeneratedBy(q("gen2"),
							pFactory.newEntityRef(q("e1")),
							null,
							pFactory.newActivityRef(q("a1")));
	makeDocAndTest(gen,"target/generation2.xml");
    }


    public void testGeneration3() throws JAXBException  {
	WasGeneratedBy gen = pFactory.newWasGeneratedBy(q("gen3"),
							pFactory.newEntityRef(q("e1")),
							"role",
							pFactory.newActivityRef(q("a1")));
	makeDocAndTest(gen,"target/generation3.xml");
    }


    public void testGeneration4() throws JAXBException  {
	WasGeneratedBy gen = pFactory.newWasGeneratedBy(q("gen4"),
							pFactory.newEntityRef(q("e1")),
							"role",
							pFactory.newActivityRef(q("a1")));
	gen.setTime(pFactory.newTimeNow());
	makeDocAndTest(gen,"target/generation4.xml");
    }
}
