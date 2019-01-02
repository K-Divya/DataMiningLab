#Generate association rule using apriori and visualize them

install.packages("arules")
install.packages("arulesViz")

library(arules)
library(arulesViz)

#load data
data("Groceries")

#view data
summary(Groceries)
inspect(Groceries[1:5])

#View top 20 most frequently bought items
itemFrequencyPlot(Groceries,topN=20,type="absolute")

#Obtain and sort rules according to confidence
basket_rules <- apriori(Groceries,parameter = list(sup = 0.01, conf = 0.5,target="rules"))
basket_rules<-sort(basket_rules, by="confidence", decreasing=TRUE)

#View rules
summary(basket_rules)
inspect(basket_rules)

#graph of the rules
plot(basket_rules,method = "graph")
