# Read data
statistics <- read.csv("WTProjectV2.csv")
statistics

# Research Points earned
hist(statistics$RP.Earned, 
        main="Research Points earned per match", 
        ylab="Matches",
        xlab="Research points (RP)") 
barplot(statistics$RP.Earned, 
        main="Research Points earned per match", 
        xlab="Matches",
        ylab="Research points (RP)") 

# Net Silver Lion Earnings
hist(statistics$Net.SL.Earnings, 
     main="Net Silver Lions earned per match", 
     xlab="Silver Lions (SL)")
boxplot(statistics$Net.SL.Earnings, 
        ylab="Silver Lions (SL) Earned", 
        main="Net Silver Lions earned per match")

# Proportion of wins and losses
outcomes <- table(statistics$Outcome.of.Match)
outcomes
