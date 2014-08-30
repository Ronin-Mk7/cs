import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcPI {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Calculate PI
		BigDecimal pi = new BigDecimal(0);
		BigDecimal denominator = new BigDecimal(1);
		final BigDecimal numerator = new BigDecimal(4.0);
		final BigDecimal addend = new BigDecimal(2.0);
		
		while(true) {
			//final BigDecimal oldValue = pi;	 //finals can only be initialized once
			pi = pi.add(numerator.divide(denominator, 10000, RoundingMode.HALF_EVEN)); //add 4/denominator to pi
			System.out.println(pi);
			if (denominator.signum() == -1) { denominator = denominator.subtract(addend); }
			else { denominator = denominator.add(addend); }
			denominator = denominator.multiply(new BigDecimal(-1));			
		}
		
	}

}
