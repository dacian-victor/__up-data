package com.btc.ep.tc.ptable.ui.menu;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.junit.Before;
import org.junit.Test;

import com.btc.ep.tc.ptable.ui.handlers.TCDeleteStepHandler;
import com.btc.ep.tc.ptable.ui.handlers.TCHandler;
import com.btc.ep.tc.ptable.ui.menu.items.TCBaseItem;
import com.btc.ep.tc.ptable.ui.menu.items.TCSeparatorItemImpl;

/**
 * This class ....
 * 
 * @author dacian.victor
 */
public class UT_TCPTableMenuExtensionManager {

    private TCPTableMenuExtensionManager sut;

    private IExtensionRegistry registry;

    /**
     * initializes before running a particular test
     * 
     * @throws Exception anything
     */
    @Before
    public void setUp() throws Exception {

        sut = spy(new TCPTableMenuExtensionManager());
        registry = mock(ExtensionRegistry.class);

        sut.setIExtensionRegistry(registry);
    }

    /**
     * 
     * Test getRegisterExtensions on registry
     *
     */
    @Test
    public void test_getRegisterExtensions() throws Exception {

        TCHandler handler = mock(TCDeleteStepHandler.class);
        IConfigurationElement extension1 = mock(ConfigurationElementHandle.class);
        IConfigurationElement extension2 = mock(ConfigurationElementHandle.class);
        IConfigurationElement extension3 = mock(ConfigurationElementHandle.class);

        when(extension1.getName()).thenReturn("menu");
        when(extension1.getAttribute("id")).thenReturn("id1");
        when(extension1.getAttribute("keyBinding")).thenReturn("CTRL+L");
        when(extension1.createExecutableExtension("handler")).thenReturn(handler);
        when(extension1.getAttribute("icon")).thenReturn("platform:com.btc.ep/icons/icon.png");
        when(extension2.getName()).thenReturn("menu");
        when(extension2.getAttribute("id")).thenReturn("id2");
        when(extension2.createExecutableExtension("handler")).thenReturn(handler);
        when(extension3.getName()).thenReturn("separator");
        when(extension3.getAttribute("id")).thenReturn("id3");

        doNothing().when(sut).injectContext(handler);
        IConfigurationElement[] extensions = new ConfigurationElementHandle[3];
        extensions[0] = extension1;
        extensions[1] = extension2;
        extensions[2] = extension3;
        when(registry.getConfigurationElementsFor(TCPTableMenuExtensionManager.PTABLE_MENU_ID)).thenReturn(extensions);

        // call register extensions
        List<TCBaseItem> contributionsList = sut.getRegisterExtensions();

        // verify size
        assertEquals(extensions.length, contributionsList.size());

    }

    /**
     * 
     * Test menu sorting
     *
     */
    @Test
    public void test_getExtensionsByPositionInList() {
        String id1 = "id1";
        String id2 = "id2";
        String id3 = "id3";
        String id4 = "id4";
        TCBaseItem item1 = new TCSeparatorItemImpl(id1, "after:" + id2);
        TCBaseItem item2 = new TCSeparatorItemImpl(id2, null);
        TCBaseItem item3 = new TCSeparatorItemImpl(id3, "after:" + id2);
        TCBaseItem item4 = new TCSeparatorItemImpl(id4, "after:" + id1);

        Map<String, TCBaseItem> contributionsMap = new HashMap<String, TCBaseItem>();
        contributionsMap.put(item1.getId(), item1);
        contributionsMap.put(item2.getId(), item2);
        contributionsMap.put(item3.getId(), item3);
        contributionsMap.put(item4.getId(), item4);

        List<TCBaseItem> contributionsList = sut.getExtensionsByPositionInList(contributionsMap);
        // verify size
        assertEquals(contributionsMap.size(), contributionsList.size());

        // verify correct order
        assertEquals(item2, contributionsList.get(0));
        assertEquals(item1, contributionsList.get(1));
        assertEquals(item4, contributionsList.get(2));
        assertEquals(item3, contributionsList.get(3));
    }

    /**
     * 
     * Test menu sorting
     *
     */
    @Test
    public void test_getExtensionsByPositionInList2() {
        String id1 = "id1";
        String id2 = "id2";
        String id3 = "id3";
        TCBaseItem item1 = new TCSeparatorItemImpl(id1, "swd" + id2);
        TCBaseItem item2 = new TCSeparatorItemImpl(id2, null);
        TCBaseItem item3 = new TCSeparatorItemImpl(id3, "after:" + id1);

        Map<String, TCBaseItem> contributionsMap = new LinkedHashMap<String, TCBaseItem>();
        contributionsMap.put(item1.getId(), item1);
        contributionsMap.put(item2.getId(), item2);
        contributionsMap.put(item3.getId(), item3);

        List<TCBaseItem> contributionsList = sut.getExtensionsByPositionInList(contributionsMap);
        // verify size
        assertEquals(contributionsMap.size(), contributionsList.size());

        // verify correct order
        assertEquals(item1, contributionsList.get(0));
        assertEquals(item3, contributionsList.get(1));
        assertEquals(item2, contributionsList.get(2));
    }

}
