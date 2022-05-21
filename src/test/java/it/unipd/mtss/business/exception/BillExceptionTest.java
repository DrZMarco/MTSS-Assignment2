////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business.exception;
import org.junit.Test;


public class BillExceptionTest {
    @Test(expected = BillException.class)
    public void constructor_emptyConstructor_ExceptionThrownWithEmptyConstructor() throws BillException {
        throw new BillException();
    }

    @Test(expected = BillException.class)
    public void constructor_messageConstructor_ExceptionThrownWithMessageConstructor() throws BillException {
        throw new BillException("Thrown new exception");
    }
}