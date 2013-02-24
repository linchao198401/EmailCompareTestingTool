package org.performance.web.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.performance.web.common.AC;
import org.performance.web.dao.model.Template;
import org.performance.web.dao.model.TemplateGroup;
import org.performance.web.dao.model.TemplateSequence;

public class TemplateGroupServiceTest {

    TemplateGroupService service = AC.getBean(TemplateGroupService.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSave() {
        TemplateGroup group = new TemplateGroup();
        group.setName("groupName");
        group.setDomain("domain");

        Template template = new Template();
        template.setDomain("template");
        template.setContent("content");

        TemplateSequence sequence = new TemplateSequence();
        sequence.setSequence(1);
        sequence.setTemplate(template);
        sequence.setTemplateGroup(group);

        Set<TemplateSequence> set = new HashSet<TemplateSequence>();
        set.add(sequence);
        group.setTemplateSequences(set);

        service.addTemplateGroup(group);

        TemplateGroup group2 = service.getTemplateGroup(group.getId());
        System.out.println(group2);
        TemplateSequence sequence2 = group2.getTemplateSequences().iterator().next();
        System.out.println(sequence2);
        Template template2 = sequence2.getTemplate();
        System.out.println(template2);

    }

    @Test
    public void testDelete() {
        TemplateGroup group = new TemplateGroup();
        group.setName("groupName");
        group.setDomain("domain");

        Template template = new Template();
        template.setDomain("template");
        template.setContent("content");

        TemplateSequence sequence = new TemplateSequence();
        sequence.setSequence(1);
        sequence.setTemplate(template);
        sequence.setTemplateGroup(group);

        Set<TemplateSequence> set = new HashSet<TemplateSequence>();
        set.add(sequence);
        group.setTemplateSequences(set);

        service.addTemplateGroup(group);

        TemplateGroup group2 = service.getTemplateGroup(group.getId());
        System.out.println(group2);
        TemplateSequence sequence2 = group2.getTemplateSequences().iterator().next();
        System.out.println(sequence2);
        Template template2 = sequence2.getTemplate();
        System.out.println(template2);

        service.deleteTemplateGroup(group2.getId());

    }

}
