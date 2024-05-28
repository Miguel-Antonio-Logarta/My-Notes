# what does this command do?
getwd()

# do you remember how we solved the issue where R can't find the file we're trying to read in?

# there's another way to get R to see the file: 
wd()
getwd()

# after you set the folder to the right path then ...

# look at data quickly to check

# check type

# Let's grab the sleep data again

## look at what the column is called

# grab column

# store column
sleep <-
  
  # you can also call the column by its number
  
  # note: in R everything starts from index 1 (not 0)
  # what is this data type?
  
  # why does that matter?
  # this histogram works   
  
  # this histogram won't work
  
# how do you think we fix this problem?
  commute<- read.csv("commute_oneColumn.csv", encoding="UTF-8")
  hist(commute)

# Try all three commands and then answer the last iLearn quiz: 
## 1st one

## 2nd one

## 3rd one

# what else do we need to be careful of?
# let's look at the inputs for read.csv by bringing up the help page

commute <- read.csv("commute_oneColumn.csv", encoding="UTF-8")

# first few rows of data

# Time to check the raw data file!
# Chatblast! Why is seeing the X130 header for this dataset a problem?

# fix:

# check:

# Caution: there are some other ways that reading files in can go wrong. Some students find that some csv files add in extra rows of data with strange symbol. I think this occurs because the way my computer created the file isn't how your computer reads it out. 

# Solution? Copy-paste the data into your own spreadsheet and try to read your file in instead. If that doesn't work, come to office hours! I want to figure it out with you!