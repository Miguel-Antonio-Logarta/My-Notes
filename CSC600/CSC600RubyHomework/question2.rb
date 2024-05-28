class Array
    def limited(amin, amax)
        # Checks if all values of the array are in between the values amin and amax
        self.each do | value |
            if value < amin || value > amax
                return false
            end
            return true
        end
    end
    
    def sorted()
        i = 0
        UNSORTED = 0
        SORTED_DECREASING = -1
        SORTED_INCREASING = 1

        if self.length() <= 1
            return SORTED_INCREASING
        end

        # While we loop we can keep memory of the previous value
        # sorted_type = SORTED_INCREASING
        # allSameValues = true
        # For each element
        # if descending and the current element > last visited element, return false
        # if ascending and the current element < last visited element, return true
        # if the same value, keep going
        # else, allSameValues should be false
        # After loop is finished, return sorted_type. However, if all the values are the same, return SORTED_INCREASING

        # If the second value is the same, keep iterating, until we come to the first different element
        while 

        if self[0] > self[1]
            # Check if all the values are decreasing

        elsif
            # Check if all the values are increasing
        end

    end
end

limited_values = [1,2,3,4,5]
limited_test1 = values.limited(0,10)
limited_test2 = values.limited(3,4)

puts limited_test1
puts limited_test2

sorted_values1 = [1]        # Should return sorted ascending (neither ascending nor descending)
sorted_values2 = [1,1,1]    # Should return sorted ascending (neither ascending nor descending)
sorted_values3 = [1,1,2]    # Should return sorted ascending
sorted_values4 = [1,1,0]    # Should return sorted descending
sorted_values5 = [1,1,0,1,2] # Should return unsorted
sorted_values5 = [0,2,1]    # Should return unsorted

# All should return true
puts sorted_values1.sorted() == 1
puts sorted_values2.sorted() == 1
puts sorted_values3.sorted() == 1
puts sorted_values4.sorted() == -1
puts sorted_values5.sorted() == 0
puts sorted_values5.sorted() == 0




