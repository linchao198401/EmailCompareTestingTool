package org.performance.web.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.performance.web.common.util.FreeMarkerUtil;

public class FreeMarkerUtilTest {

    static BufferedReader br;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        br = new BufferedReader(new FileReader(new File("/workspace/work/Body_SendRFP_ToPlanner_ForAccor.ftl")));
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
    public void test() throws Exception {
        String s = null;
        String f = "";
        while ((s = br.readLine()) != null) {
            f += "\n" + s;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("RFP_PLANNER_PROFILE", getValue());
        map.put("SOURCE_URL", "SOURCE_URL");
        System.out.println(FreeMarkerUtil.filterByFreeMarker(f, map));
    }

    private Object getValue() throws Exception {

        DynaProperty[] beanProperties = new DynaProperty[] { new DynaProperty("prefix", String.class),
                new DynaProperty("firstName", String.class), new DynaProperty("lastName", String.class) };

        BasicDynaClass dynaBeanClass = new BasicDynaClass("politician", BasicDynaBean.class, beanProperties);
        DynaBean dynaBean = dynaBeanClass.newInstance();
        dynaBean.set("prefix", "prefix");
        dynaBean.set("firstName", "firstName");
        dynaBean.set("lastName", "lastName");
        return dynaBean;

    }
}
