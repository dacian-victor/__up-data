package com.btc.ep.vector.tc.db.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.btc.ep.core.resourceservice.internal.services.ResourceServiceImpl;
import com.btc.ep.core.resourceservice.services.ResourceService;
import com.btc.ep.vector.accessor.Identifier;
import com.btc.ep.vector.accessor.Identifier.IdentifierKind;
import com.btc.ep.vector.accessor.ModifiableHeaderDefinition;
import com.btc.ep.vector.accessor.ModifiableVectorSource;
import com.btc.ep.vector.accessor.RandomAccessWriteVector;
import com.btc.ep.vector.accessor.Step;
import com.btc.ep.vector.tc.services.TCVectorSourceProvider;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * This class ....
 * 
 * @author me
 */
public class UT_TCVectorObjectSG {

    private TCVectorSourceProvider provider;
    private ModifiableHeaderDefinition headerDefinition;

    private Identifier in1;
    private Identifier in2;
    private Identifier param1;
    private Identifier param2;
    private Identifier loc1;
    private Identifier loc2;
    private Identifier out1;
    private Identifier out2;
    private Identifier in3;

    @Before
    public void init() {
        ResourceService resourceService = new ResourceServiceImpl();
        provider = new TCVectorSourceProvider();
        provider.setResourceService(resourceService);

        headerDefinition = new ModifiableHeaderDefinition("MyArch", "MyScope", "MyVecName", true);

        in1 = new Identifier("In1", IdentifierKind.INPUT);
        in2 = new Identifier("In2", IdentifierKind.INPUT);
        in3 = new Identifier("In3", IdentifierKind.INPUT);
        List<Identifier> inputs = Lists.newArrayList(in1, in2);

        param1 = new Identifier("Param1", IdentifierKind.PARAMETER);
        param2 = new Identifier("Param2", IdentifierKind.PARAMETER);
        List<Identifier> params = Lists.newArrayList(param1, param2);

        loc1 = new Identifier("Loc1", IdentifierKind.LOCAL);
        loc2 = new Identifier("Loc2", IdentifierKind.LOCAL);
        List<Identifier> locals = Lists.newArrayList(loc1, loc2);

        out1 = new Identifier("Out1", IdentifierKind.OUTPUT);
        out2 = new Identifier("Out2", IdentifierKind.OUTPUT);
        List<Identifier> outputs = Lists.newArrayList(out1, out2);

        headerDefinition.addIdentifiers(inputs, IdentifierKind.INPUT);
        headerDefinition.addIdentifiers(params, IdentifierKind.PARAMETER);
        headerDefinition.addIdentifiers(locals, IdentifierKind.LOCAL);
        headerDefinition.addIdentifiers(outputs, IdentifierKind.OUTPUT);
    }

    /**
     * 
     * Test creation of signal generators
     *
     */
    @Test
    public void testAddSignalGenerator() {
        File file = Files.createTempDir();
        file.deleteOnExit();

        // Creating the vector.
        RandomAccessWriteVector vectorObject = provider.createVectorObject(headerDefinition, file);
        ModifiableVectorSource<? extends RandomAccessWriteVector> vectorSource = vectorObject.getSource();

        List<Identifier> ids = Lists.newArrayList(in1, in2);
        List<Step> steps = new ArrayList<>();

        steps.add(new StepUT(7, ids, "ValueStep7"));

        vectorObject.setSteps(steps);

        vectorObject.markAsSignalGenerator(new HashMap<>(), in1, 2, 3);

        assertNull(vectorObject.getSignalGenerator(in1, 1));
        assertNotNull(vectorObject.getSignalGenerator(in1, 2));
        assertNotNull(vectorObject.getSignalGenerator(in1, 3));
        assertNotNull(vectorObject.getSignalGenerator(in1, 4));
        assertNull(vectorObject.getSignalGenerator(in1, 5));

        vectorSource.close();
        file.delete();
    }

    /**
     * 
     * Test creation of signal generators
     *
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddSignalGeneratorOnExistingSG() {
        File file = Files.createTempDir();
        file.deleteOnExit();

        // Creating the vector.
        RandomAccessWriteVector vectorObject = provider.createVectorObject(headerDefinition, file);
        ModifiableVectorSource<? extends RandomAccessWriteVector> vectorSource = vectorObject.getSource();

        List<Identifier> ids = Lists.newArrayList(in1, in2);
        List<Step> steps = new ArrayList<>();

        steps.add(new StepUT(7, ids, "ValueStep7"));

        vectorObject.setSteps(steps);

        vectorObject.markAsSignalGenerator(new HashMap<>(), in1, 2, 2);
        vectorObject.markAsSignalGenerator(new HashMap<>(), in1, 3, 3);

        vectorSource.close();
        file.delete();
    }

    /**
     * 
     * Test creation of signal generators invalid identifier
     *
     */
    @Test (expected = RuntimeException.class)
    public void testAddSignalGeneratorInvalidIdentifier() {
        File file = Files.createTempDir();
        file.deleteOnExit();

        // Creating the vector.
        RandomAccessWriteVector vectorObject = provider.createVectorObject(headerDefinition, file);
        ModifiableVectorSource<? extends RandomAccessWriteVector> vectorSource = vectorObject.getSource();

        List<Identifier> ids = Lists.newArrayList(in1);
        List<Step> steps = new ArrayList<>();

        steps.add(new StepUT(7, ids, "ValueStep7"));

        vectorObject.setSteps(steps);

        vectorObject.markAsSignalGenerator(new HashMap<>(), in3, 2, 2);

        vectorSource.close();
        file.delete();
    }

    /**
     * 
     * Test creation of signal generators invalid step
     *
     */
    @Test (expected = IllegalArgumentException.class)
    public void testAddSignalGeneratorInvalidStep() {
        File file = Files.createTempDir();
        file.deleteOnExit();

        // Creating the vector.
        RandomAccessWriteVector vectorObject = provider.createVectorObject(headerDefinition, file);
        ModifiableVectorSource<? extends RandomAccessWriteVector> vectorSource = vectorObject.getSource();

        List<Identifier> ids = Lists.newArrayList(in1);
        List<Step> steps = new ArrayList<>();

        steps.add(new StepUT(2, ids, "ValueStep7"));

        vectorObject.setSteps(steps);

        vectorObject.markAsSignalGenerator(new HashMap<>(), in2, 2, 2);

        vectorSource.close();
        file.delete();
    }

    /**
     * 
     * Test remove of signal generators
     *
     */
    @Test
    public void removesSignalGeneratorForValidInputParameters() {
        File file = Files.createTempDir();
        file.deleteOnExit();

        // Creating the vector.
        RandomAccessWriteVector vectorObject = provider.createVectorObject(headerDefinition, file);
        ModifiableVectorSource<? extends RandomAccessWriteVector> vectorSource = vectorObject.getSource();

        List<Identifier> ids = Lists.newArrayList(in1, in2);
        List<Step> steps = new ArrayList<>();

        steps.add(new StepUT(7, ids, "ValueStep7"));

        vectorObject.setSteps(steps);

        vectorObject.markAsSignalGenerator(new HashMap<>(), in1, 2, 2);

        assertNotNull(vectorObject.getSignalGenerator(in1, 2));
        assertNotNull(vectorObject.getSignalGenerator(in1, 3));

        vectorObject.removeSignalGenerator(in1, 2);

        assertNull(vectorObject.getSignalGenerator(in1, 2));
        assertNull(vectorObject.getSignalGenerator(in1, 3));

        vectorSource.close();
        file.delete();
    }

    /**
     * 
     * Test remove of signal generators not existing id
     *
     */
    @Test (expected = IllegalArgumentException.class)
    public void throwsExceptionWhenNonExistingSignalGeneratorShouldBeRemoved() {
        File file = Files.createTempDir();
        file.deleteOnExit();

        // Creating the vector.
        RandomAccessWriteVector vectorObject = provider.createVectorObject(headerDefinition, file);
        ModifiableVectorSource<? extends RandomAccessWriteVector> vectorSource = vectorObject.getSource();
        vectorObject.removeSignalGenerator(new Identifier("invalid"), 5);

        vectorSource.close();
        file.delete();
    }

}
