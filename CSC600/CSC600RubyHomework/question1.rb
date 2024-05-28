#!/usr/bin/ruby -w

# While ------------------------
# Syntax of while 
# while [condition] do
#   ...
# end
def make_triangle(height)
    counter = 0
    while counter < height do
        puts "*" * counter
        counter += 1
    end
end

make_triangle(5)

# Outputs
# *
# **
# ***
# ****

# Until ------------------------
# Until is exact same as while loop

def keep_multiplying(n, m)
    starting_number = 1
    until starting_number >= m do
        starting_number = starting_number * n
        puts starting_number
    end
end

keep_multiplying(5, 100)

# Outputs
# 5
# 25
# 125



# For ------------------------
# Similar to python's for loop
# for [iterator] in [iterable] do
#   ...
# end
def count_evens(values)
    num_evens = 0
    for number in values do
        if number % 2 == 0
            num_evens += 1
        end
    end
    puts "There are #{num_evens} even numbers"
end

count_evens([1,2,3,4,5,6,7,8,9,10])

# Outputs
# There are 5 even numbers


# Upto ------------------------
# [starting value].upto([ending value]) do | index | 
#   ...
# end
def multiples_of_n(low, high, n)
    multiples = 0
    low.upto(high) do | currentnumber |
        if currentnumber % n == 0
            multiples += 1
        end
    end
    puts "There are #{multiples} multiples of #{n} from #{low} to #{high}"
end

multiples_of_n(30, 45, 3)

# Outputs
# There are 6 multiples of 3 from 30 to 45


# Downto ------------------------
# Opposite of upto, it decrements
# [high number].downto([low number]) do | index |
#   ...
# end
def bottles_of_beer(number_of_beer_bottles)
    number_of_beer_bottles.downto(1) do | current_bottles |
        song_lyrics ="#{current_bottles} bottles of beer on the wall.\n#{current_bottles} bottles of beer.\nTake one down, pass it around,\n#{current_bottles - 1} bottles of beer on the wall.\n\n"
        puts song_lyrics
    end

    song_lyrics = "No more bottles of beer on the wall,\nno more bottles of beer.\nGo to the store and buy some more,\n#{number_of_beer_bottles} bottles of beer on the wall..."

    puts song_lyrics
end

bottles_of_beer(5)

# Outputs
# 5 bottles of beer on the wall.
# 5 bottles of beer.
# Take one down, pass it around,
# 4 bottles of beer on the wall.

# 4 bottles of beer on the wall.
# 4 bottles of beer.
# Take one down, pass it around,
# 3 bottles of beer on the wall.

# 3 bottles of beer on the wall.
# 3 bottles of beer.
# Take one down, pass it around,
# 2 bottles of beer on the wall.

# 2 bottles of beer on the wall.
# 2 bottles of beer.
# Take one down, pass it around,
# 1 bottles of beer on the wall.

# 1 bottles of beer on the wall.
# 1 bottles of beer.
# Take one down, pass it around,
# 0 bottles of beer on the wall.

# No more bottles of beer on the wall,
# no more bottles of beer.
# Go to the store and buy some more,
# 5 bottles of beer on the wall...


# Times ------------------------
# Does a thing n times
# [number].times do | index |
# end
def say_it_twice(saying)
    2.times do | i |
        puts saying
    end
end

say_it_twice("yipee!")

# Outputs
# yipee!
# yipee!


# Each ------------------------
# Works like a for loop, but is better
# [iterable].each do | iterator | 
#   ...
# end
# The iterator variable is created in its own scope unlike for
def print_doubled(values)
    values.each do | value |
        puts value * 2
    end
end

print_doubled([1,2,3,4,5])

# Outputs
# 2
# 4
# 6
# 8
# 10

# Map ------------------------
# var = [iterable].map do | [iterator] |
#   ...
# end
# Original array is not modified, but a new array is returned and assigned to a variable
def exponentiate_array(values, n)
    new_vals = values.map do | value |
        value ** n
    end
    print new_vals
    puts ""
end

exponentiate_array([1,2,3,4,5], 3)

# Outputs
# [1, 8, 27, 64, 125]

# Step ------------------------
# Works like python's range where you can specify the step
# [low number].step([high number], [step]) do | iterator |
#   ...
# end
def add_every_n_number(values, n)
    sum = 0
    0.step(values.length(), n) do | value |
        # code to execute for each value
        sum += value
    end
    puts sum
end

add_every_n_number([1,2,3,4,5,6], 2)

# Outputs
# 12

# Collect ------------------------
# Exact same as map, don't need to remember this one
def divide_by_n(values, n)
    new_vals = values.collect do | value |
        value = value.to_f / n.to_f
    end
    print new_vals
    puts ""
end

divide_by_n([1,2,3,4,5], 3)

# Outputs
# [0.3333333333333333, 0.6666666666666666, 1.0, 1.3333333333333333, 1.6666666666666667]

# Select ------------------------
# Select will return a new array / hash map based on a condition

scores = {
  Mat: 91,
  Mandy: 85,
  Evan: 95,
  Kylie: 55,
  Cynthia: 60,
  Marcus: 68
}

def greater_than_n(values, n)
    values.select { | k, v | v > n }
end

good_scores = greater_than_n(scores, 90)
puts scores
puts good_scores

# Outputs
# {:Mat=>91, :Evan=>95}

# Reject ------------------------
def less_than_n(values, n)
    values.reject { | k, v | v > n}
end
needs_imrovement = less_than_n(scores, 70)
puts needs_imrovement

# Outputs
# {:Kylie=>55, :Cynthia=>60, :Marcus=>68}

# Things to study:
# while do
# each instead of for -> always modifies the original array
# map/collect -> always returns a new array
# select -> returns a new array from og array based on if it passed the condition
# reject -> returns a new array from og array based on if it failed the condition
# Array insertion in ruby
#   arr[0] to get value
#   arr.insert(array index, element)
#   arr.append(value) to append to original array 