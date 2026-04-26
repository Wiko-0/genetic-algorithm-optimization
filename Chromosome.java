public class Chromosome implements Cloneable {
    private long bits;
    private final long xMaxBits;
    private final long yMaxBits;
    private final double xMin;
    private final double yMin;
    private final int accuracy;
    private final int xGeneLength;
    private final int yGeneLength;

    Chromosome(double x, double y, double xMax, double xMin, double yMax, double yMin, int accuracy) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.accuracy = accuracy;
        this.xMaxBits = encodeX(xMax);
        this.yMaxBits = encodeY(yMax);
        this.xGeneLength = calculateGeneLength(xMax, xMin, accuracy);
        this.yGeneLength = calculateGeneLength(yMax, yMin, accuracy);
        this.bits = encodeX(x) | (encodeY(y) << this.xGeneLength);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /*
    Crosses 2 chromosomes at the selected position.
     */
    public static void cross(Chromosome chromosome1, Chromosome chromosome2, int position) {
        long bits1 = chromosome1.getBits();
        long bits2 = chromosome2.getBits();
        long tmpBits = bits1 & (-1L >>> (64-position));
        bits1 &= -1L << position;
        bits1 |= bits2 & (-1L >>> (64-position));
        bits2 &= -1L << position;
        bits2 |= tmpBits;
        chromosome1.setBits(bits1);
        chromosome2.setBits(bits2);
    }

    /*
    Mutates the chromosome at the selected position.
     */
    public void mutate(int position) {
        bits ^= (1L << position);
    }

    /*
    Decodes and returns the floating-point value of the first gene.
    */
    public double getX() {
        return decodeX(extractXBits());
    }

    /*
    Decodes and returns the floating-point value of the second gene.
     */
    public double getY() {
        return decodeY(extractYBits());
    }

    /*
    Returns the binary representation of the chromosome.
     */
    public long getBits() {
        return bits;
    }

    /*
    Sets the binary representation of the chromosome.
     */
    public void setBits(long bits) {
        this.bits = bits;
    }

    /*
    Returns the length of the chromosome.
     */
    public int getLength() {
        return this.xGeneLength + this.yGeneLength;
    }

    /*
    Repairs the chromosome's genes.
     */
    public void fix() {
        if (xMaxBits < extractXBits())
            fixX();
        if (yMaxBits < extractYBits())
            fixY();
    }

    /*
    Repairs the value of the first gene.
     */
    private void fixX() {
        bits &= xMaxBits;
    }

    /*
    Repairs the value of the second gene.
     */
    private void fixY() {
        bits &= (yMaxBits << xGeneLength);
    }

    /*
    Encodes the value of the first gene into a binary value.
     */
    private long encodeX(double x) {
        return (long)((x-xMin)*Math.pow(10, accuracy));
    }

    /*
    Encodes the value of the second gene into a binary value.
     */
    private long encodeY(double y) {
        return (long)((y-yMin)*Math.pow(10, accuracy));
    }

    /*
    Decodes the binary value of the first gene.
     */
    private double decodeX(long bits) {
        return xMin + bits/Math.pow(10, accuracy);
    }

    /*
    Decodes the binary value of the second gene.
     */
    private double decodeY(long bits) {
        return yMin + bits/Math.pow(10, accuracy);
    }

    /*
    Returns the binary representation of the first gene.
     */
    private long extractXBits() {
        return bits & (-1L >>> (64-xGeneLength));
    }

    /*
    Returns the binary representation of the second gene.
     */
    private long extractYBits() {
        return (bits & ((-1L >>> (64-yGeneLength)) << xGeneLength)) >>> xGeneLength;
    }

    /*
    Calculates the gene length (number of bits required for its representation).
     */
    private static int calculateGeneLength(double max, double min, int accuracy) {
        return (int)(Math.log((max-min)*Math.pow(10, accuracy))/Math.log(2))+1;
    }
}