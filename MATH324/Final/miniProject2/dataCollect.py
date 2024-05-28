# Collect data from championStats.html
from bs4 import BeautifulSoup
import csv

winrateData = []
championRangeType = {}
collectedData = []

def readWinrate():
    with open("championStats.html", encoding="utf8") as fp:
        soup = BeautifulSoup(fp, "html.parser")
        table = soup.select_one(".⚡516a5f38 > div:first-child")
        
        # Now that we have the table, loop through each row / children
    for row in table.children:
        championTag = row.select_one(".championKey > span")
        winRateTag = row.select_one(".⚡a40f4298 > span")
        championName = championTag.text
        winRate = winRateTag.text
        winrateData.append({
            "champion": championName,
            "winRate": winRate
        })


def getRangeType():
    with open("championInformation.csv") as csvFile:
        reader = csv.DictReader(csvFile)
        for row in reader:
            # print(row["Champion"], row["Range type"])
            championRangeType[row["Champion"]] = row["Range type"]

def mapRanges():
    for winrate in winrateData:
        rangeType = championRangeType[winrate["champion"]]
        winrate["rangeType"] = rangeType
        collectedData.append({
            "champion": winrate["champion"],
            "winRate": winrate["winRate"],
            "rangeType": rangeType
        })

def outputToSeparateCSVFiles():
    with open("rangedWinRates.csv", "w", newline='') as f:
        writer = csv.writer(f)
        header = ["champion", "winRate", "rangeType"]
        writer.writerow(header)

        for champion in collectedData:
            if champion["rangeType"] == "Ranged":
                writer.writerow([
                                champion["champion"], 
                                float(champion["winRate"].replace('%', 'e-2')), 
                                champion["rangeType"]
                                ])
    
    with open("meleeWinRates.csv", "w", newline='') as f:
        writer = csv.writer(f)
        header = ["champion", "winRate", "rangeType"]
        writer.writerow(header)

        for champion in collectedData:
            if champion["rangeType"] == "Melee":
                writer.writerow([
                                champion["champion"], 
                                float(champion["winRate"].replace('%', 'e-2')), 
                                champion["rangeType"]
                                ])


def collectData():
    readWinrate()
    getRangeType()
    mapRanges()
    outputToSeparateCSVFiles()

    


if __name__ == "__main__": 
    collectData()