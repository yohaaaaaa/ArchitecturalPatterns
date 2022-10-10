package datasource;

import org.junit.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SingleTableGatewayTest {
    private final Connection conn = DatabaseConnection.getInstance().getConnection();
//    @Test
//    public void testCreateTable() {
//        SingleTableGateway.createTable();
//    }

    @Test
    public void testCreateChemical() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createChemical("Boron");
        assertEquals("Boron", gateway.getName());
    }

    @Test
    public void testCreateAcid() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createAcid("Carbonic Acid", 1, 1);
        assertEquals("Carbonic Acid", gateway.getName());
        assertEquals(1, gateway.getSolute());
        assertEquals(1, gateway.getDissolves());
    }

    @Test
    public void testCreateCompound() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createCompound("H2O", 1, 1);
        assertEquals("H2O", gateway.getName());
        assertEquals(1, gateway.getCompoundID());
        assertEquals(1, gateway.getElementID());
    }

    @Test
    public void testCreateBase() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createBase("Bleach", 1);
        assertEquals("Bleach", gateway.getName());
        assertEquals(1, gateway.getSolute());
    }

    @Test
    public void testCreateElement() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createElement("Fire", 1, 2);
        assertEquals("Fire", gateway.getName());
        assertEquals(1, gateway.getAtomicNum());
        assertEquals(2, gateway.getAtomicMass(), 0.0001);
    }

    @Test
    public void testCreateMetal() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createMetal("Steel", 1, 2, 3);
        assertEquals("Steel", gateway.getName());
        assertEquals(1, gateway.getAtomicNum());
        assertEquals(2, gateway.getAtomicMass(), 0.0001);
        assertEquals(3, gateway.getDissolvedBy());
    }

    @Test
    public void testDelete() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createChemical("chemicalToBeDeleted");
        assertEquals("chemicalToBeDeleted", gateway.getName());
        assertTrue(gateway.delete());
    }

    @Test
    public void testPersist() throws SQLException
    {
        conn.setAutoCommit(false);

        SingleTableGateway gateway = SingleTableGateway.createChemical("oldChemicalName");
        assertEquals("oldChemicalName", gateway.getName());
        gateway.setName("newChemicalName");
        gateway.persist();

        assertEquals("newChemicalName", gateway.getName());
    }
}