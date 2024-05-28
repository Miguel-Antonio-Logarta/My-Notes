class Triangle 

    attr_accessor :side_a, :side_b, :side_c, :triangle_type

    def initialize(side_a, side_b, side_c)
        @side_a = side_a
        @side_b = side_b
        @side_c = side_c

        @triangle_type = classify_triangle()
        @right_triangle = is_right_triangle()
    end

    def classify_triangle()
        if @side_a == @side_b && @side_a != @side_c
            return "ISOCELES"
        elsif @side_a != @side_b && @side_a != @side_c
            return "SCALENE"
        elsif @side_a == @side_b && @side_a == @side_c
            puts "I am an equilateral"
            return "EQUILATERAL"
        else
            return "NOT_A_TRIANGLE"
        end
    end 

    def is_right_triangle()
        if @side_a ** 2 + @side_b ** 2 == @side_c ** 2
            return true
        else
            return false
        end
    end

    def perimeter()
        if @triangle_type == "NOT_A_TRIANGLE"
            puts "Not a triangle"
        else
            return @side_a + @side_b + @side_c
        end
    end

    def area()
        if @triangle_type == "NOT_A_TRIANGLE"
            puts "Not a triangle"
        else
            # Use Heron's formula since we have all three sides
            s = (@side_a.to_f + @side_b.to_f + @side_c.to_f)/2.0
            area = Math.sqrt(s * (s - side_a.to_f) * (s - side_b.to_f) * (s - side_c.to_f))
            return area
        end
    end

end

triangle = Triangle.new(5, 5, 5)
puts triangle.triangle_type