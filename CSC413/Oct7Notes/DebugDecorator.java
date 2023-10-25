/*
 * This class stores a single integer value and
 * supports manipulation of the value, while giving 
 * the user extra debugging information in the console
 */

public class DebugDecorator implements ISimpleClass {
    private final ISimpleClass obj;
    public DebugDecorator(ISimpleClass obj) {
      this.obj = obj;
    }
  
    public void setValue(int value) {
  
    }   
}
