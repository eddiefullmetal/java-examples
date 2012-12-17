package grigoriadis.javaexamples.mongo.springdata.model;


public class ProductRating
{
    public class Value
    {
        private float rating;

        private int sum;

        public Value()
        {
        }

        public float getRating()
        {
            return this.rating;
        }

        public int getSum()
        {
            return this.sum;
        }

        public void setRating(final float rating)
        {
            this.rating = rating;
        }

        public void setSum(final int sum)
        {
            this.sum = sum;
        }

    }

    private String id;

    private Value value;

    public ProductRating()
    {
    }

    public String getId()
    {
        return this.id;
    }

    public Value getValue()
    {
        return this.value;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public void setValue(final Value value)
    {
        this.value = value;
    }

}
