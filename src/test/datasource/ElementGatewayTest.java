package datasource;

import gatewayDTOs.ElementDTO;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElementGatewayTest {
    /**
     * Tests an element row can be created
     */
    @Test
    public void testCreateConstructor() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create new element row data gateway
        ElementGateway gateway = new ElementGateway("Boron", 5, 10.811);

        // Equals tests
        assertEquals(1, gateway.getId());
        assertEquals("Boron", gateway.getName());
        assertEquals(5, gateway.getAtomicNum());
        assertEquals(10.811, gateway.getAtomicMass(), 0.0001);
    }

    /**
     * Tests to make sure the RD gateway can find a row in the table by id
     */
    @Test
    public void testFindConstructor() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create an element row
        new ElementGateway("Oxygen", 8, 15.999);

        // Find that row
        ElementGateway foundElementGateway = new ElementGateway(1);

        // Equals tests
        assertEquals(1, foundElementGateway.getId());
        assertEquals("Oxygen", foundElementGateway.getName());
        assertEquals(8, foundElementGateway.getAtomicNum());
        assertEquals(15.999, foundElementGateway.getAtomicMass(), 0.0001);
    }

    /**
     * Tests an element row can be deleted
     */
    @Test
    public void testDeleteElement() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create new element row data gateway
        ElementGateway gateway = new ElementGateway("Nitrogen", 7, 14.0067);

        // Created tests
        assertEquals(1, gateway.getId());
        assertEquals("Nitrogen", gateway.getName());
        assertEquals(7, gateway.getAtomicNum());
        assertEquals(14.0067, gateway.getAtomicMass(), 0.0001);

        // Deleted test
        assertTrue(gateway.delete());
    }

    /**
     * Tests to make sure an element can be added to the database and updated
     */
    @Test
    public void testUpdatingElement() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create new element row data gateway
        ElementGateway gateway = new ElementGateway("Carbon", 1, 12.011);

        // Created tests
        assertEquals(1, gateway.getId());
        assertEquals("Carbon", gateway.getName());
        assertEquals(1, gateway.getAtomicNum());
        assertEquals(12.011, gateway.getAtomicMass(), 0.0001);

        // Fix the atomic number and test
        gateway.setAtomicNum(6);
        assertEquals(6, gateway.getAtomicNum());

        // Update test
        assertTrue(gateway.update());
    }

    /**
     * Tests multiple elements have the correct incrementing ID
     */
    @Test
    public void testCorrectID() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create elements
        ElementGateway carbonGateway = new ElementGateway("Carbon", 1, 12.011);
        ElementGateway nitrogenGateway = new ElementGateway("Nitrogen", 7, 14.0067);
        ElementGateway boronGateway = new ElementGateway("Boron", 5, 10.811);

        // Test ID increment
        assertEquals(1, carbonGateway.getId());
        assertEquals(2, nitrogenGateway.getId());
        assertEquals(3, boronGateway.getId());
    }

    /**
     * Tests to make sure the TD gateway can find a row in the table by its atomic number
     */
    @Test
    public void testFindingAnElementByIdTDG() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create an element row
        new ElementGateway("Oxygen", 8, 15.999);

        // Get that rows DTO
        ElementDTO oxygenDTO = ElementGateway.findByAtomicNumber(8);

        // Equals tests
        assert oxygenDTO != null;
        assertEquals(1, oxygenDTO.getId());
        assertEquals("Oxygen", oxygenDTO.getName());
        assertEquals(8, oxygenDTO.getAtomicNum());
        assertEquals(15.999, oxygenDTO.getAtomicMass(), 0.0001);
    }

    /**
     * Tests to make sure an element row can be found by name
     */
    @Test
    public void testFindingAnElementByName() throws DataException {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create elements
        new ElementGateway("Boron", 5, 10.811);
        new ElementGateway("Oxygen", 8, 15.999);
        new ElementGateway("Hydrogen", 1, 1.0078);

        // Get that rows DTO
        ElementDTO hydrogenDTO = ElementGateway.findByName("Hydrogen");

        // Equals tests
        assert hydrogenDTO != null;
        assertEquals(3, hydrogenDTO.getId());
        assertEquals("Hydrogen", hydrogenDTO.getName());
        assertEquals(1, hydrogenDTO.getAtomicNum());
        assertEquals(1.0078, hydrogenDTO.getAtomicMass(), 0.0001);
    }

    /**
     * Tests all rows can be retrieved
     */
    @Test
    public void testGettingAllRows() throws DataException
    {
        // Reset the id key and element table
        KeyRowDataGateway.reset();
        ElementGateway.createTable();

        // Create elements
        new ElementGateway("Carbon", 1, 12.011);
        new ElementGateway("Nitrogen", 7, 14.0067);
        new ElementGateway("Boron", 5, 10.811);
        new ElementGateway("Oxygen", 8, 15.999);
        new ElementGateway("Hydrogen", 1, 1.0078);

        // Get all rows from element table
        ArrayList<ElementDTO> elementsList = ElementGateway.findAll();
        int idToCheck = 0;

        // Check to make sure each element DTO has the correct ID
        assert elementsList != null;
        for (ElementDTO element : elementsList) {
            idToCheck++;
            assertEquals(idToCheck, element.getId());
        }

        // 5 element DTOs were checked in total
        assertEquals(elementsList.size(), 5);
    }
}
