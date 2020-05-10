package immunization.dao.util;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 
 * Class that makes it possible to append to an ObjectOutputStream. Normally
 * this is not possible as an exception is thrown. More information can be found
 * at
 * <p>
 * http://stackoverflow.com/questions/1194656/appending-to-an-objectoutputstream/1195078#1195078
 * <p>
 * http://stackoverflow.com/questions/12279245/classcastexception-when-appending-object-outputstream/12438141#12438141
 *
 */
public class AppendingObjectOutputStream extends ObjectOutputStream {

	/**
	 * Allows appends to ObjectOutputStream
	 * @param out
	 * @throws IOException
	 */
	public AppendingObjectOutputStream(OutputStream out) throws IOException {
		super(out);
	}

	/**
	 * Doesn't write header but resets instead
	 */
	@Override
	protected void writeStreamHeader() throws IOException {
		reset();
	}

}
