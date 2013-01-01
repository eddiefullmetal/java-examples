package grigoriadis.javaexamples.mongo.springdata.model;

import grigoriadis.javaexamples.mongo.springdata.repository.ProductReviewRepositoryCustom;

/**
 * This class represents the average rating of a product.
 * 
 * This class is not used to store any information in the database. It is used in order to map 
 * the <a target="_blank" href="http://docs.mongodb.org/manual/applications/map-reduce/">map-reduce</a> result.
 * 
 * 
 * @see ProductReviewRepositoryCustom#calculateProductRatings()
 * @author eddiefullmetal
 *
 */
public class ProductRating
{
    /**
     * The class that represents the value of the reduce operation.
     * It has the average rating ({@link #rating}) and the total number of reviews ({@link #sum}).
     * 
     * @author eddiefullmetal
     *
     */
    public class Value
    {
        /**
         * The average rating
         */
        private float rating;

        /**
         * The total number of ratings
         */
        private int sum;

        /**
         * Constructor.
         */
        public Value()
        {
        }

        /**
         * @return the {@link #rating}
         */
        public float getRating()
        {
            return this.rating;
        }

        /**
         * @return the {@link #sum}
         */
        public int getSum()
        {
            return this.sum;
        }

        /**
         * @param rating the {@link #rating} to set
         */
        public void setRating(final float rating)
        {
            this.rating = rating;
        }

        /**
         * @param sum the {@link #sum} to set
         */
        public void setSum(final int sum)
        {
            this.sum = sum;
        }

    }

    /**
     * The key of the reduce operation. The id of the product.
     */
    private String id;

    /**
     * The value of the reduce operation.
     */
    private Value value;

    /**
     * Constructor.
     */
    public ProductRating()
    {
    }

    /**
     * @return the {@link #id}
     */
    public String getId()
    {
        return this.id;
    }

    /**
     * @return the {@link #value}
     */
    public Value getValue()
    {
        return this.value;
    }

    /**
     * @param id the {@link #id} to set
     */
    public void setId(final String id)
    {
        this.id = id;
    }

    /**
     * @param value the {@link #value} to set
     */
    public void setValue(final Value value)
    {
        this.value = value;
    }

}
