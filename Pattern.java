package project2;

/** 
 * Pattern class-represents a pattern 
 * @author julian
 */

public class Pattern 
{
    /** The pattern we will store*/
    private String pattern;
    
 /** 
 * constructor-creates a pattern 
 * @param p is the 4 character string that makes up a pattern 
 */
    public Pattern(String p)
    {
        pattern=p;
    }
    
 /** 
 * @return the key
 */   
    public String getPattern()
    {
        return pattern;
    }
    
 /** 
 * generates a hash code for the patterns 
 * @return the hash code value 
 */
    @Override
    public int hashCode()
    {
        int prime=23;
        int result = prime*pattern.hashCode();
        return result;
    }
    
 /** 
 * checks if two patterns are equal to each other.  
 * @return true for equal or false for not equal
 */    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Pattern)
        {
            Pattern p =(Pattern) obj;
            return p.getPattern().equals(this.pattern);     
        }
        return false;
    }
}
