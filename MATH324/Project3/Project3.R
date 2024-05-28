# Read data
statistics <- read.csv("WarThunderProject3Data.csv")
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

# Get amount of entries with over 10,000 SL
SLSelect <- statistics$Net.SL.Earnings >= 10000
SLSelect
table(SLSelect)

# Get amount of entries with over 1,500 RP
RPSelect <- statistics$RP.Earned >= 1000
RPSelect
table(RPSelect)

# Combo select
ComboSelect <- statistics$Net.SL.Earnings >= 10000 & statistics$RP.Earned >= 1000
ComboSelect
table(ComboSelect)

# Pie chart of different tanks used
tanksFactor <- factor(statistics$Tank)
tanksTable <- table(tanksFactor)
hist(tanksTable, label=tanksFactor)
pie(tanksTable, main="Tank loadouts taken into battle")

# Pie chart of wins and losses
winrateFactor <- factor(statistics$Outcome.of.Match)
winrate <- table(winrateFactor)
pie(winrate, main="Defeats vs Victories")

# Proportion of wins and losses
outcomes <- table(statistics$Outcome.of.Match)
outcomes
