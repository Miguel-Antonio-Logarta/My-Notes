old_grades <- c(60, 70, 80, 90, 95)
new_grades <- c(65, 75, 85, 95, 100)
mean_old_grades <- mean(old_grades)
mean_new_grades <- mean(new_grades)
# a
mean_new_grades
# b
mean_old_grades
added_mean_old_grades <- mean_old_grades + 5
# difference
difference <- mean_new_grades - added_mean_old_grades
difference

# Question 31
grades <- c(10, 20, 40, 45, 50)
grades_double <- c(20, 40, 80, 90, 100)
# c
c <- mean(grades_double)
# d 
d <- mean(grades) * 2
ratio <- c /d
ratio

# Question 32
raw_grades <- c(60, 70, 80, 90, 95)
shifted_grades <- c(65, 75, 85, 95, 100)
sd_raw <- sd(raw_grades)
sd_shifted <- sd(shifted_grades)
difference_raw_shifted <- sd_raw - sd_shifted
difference_raw_shifted

# Question 33
# Question 32
first_quiz <- c(10, 20, 40, 45, 50)
second_quiz <- c(20, 40, 80, 90, 100)
sd_first <- sd(first_quiz)
sd_second <- sd(second_quiz)
difference_first_second <- sd_second / sd_first
difference_first_second
