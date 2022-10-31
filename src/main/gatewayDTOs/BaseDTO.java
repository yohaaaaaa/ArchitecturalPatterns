package gatewayDTOs;

/**
 * Base Dto for the Base Gateway
 */
public class BaseDTO {
    private long id;
    private long solute;

    /**
     * Base constructor
     * @param solute
     */
    public BaseDTO(long id, long solute)
    {
        this.id = id;
        this.solute = solute;
    }

    /**
     * set the solute for base
     * @param solute
     */
    public void setSolute(long solute) { this.solute = solute; }

    /**
     * get the id for base
     * @return id
     */
    public long getId() { return id; }

    /**
     * get the solute for base
     * @return solute
     */
    public long getSolute() { return solute; }
}
