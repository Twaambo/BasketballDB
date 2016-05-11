import csv

def playerdata():
    with open("players.csv") as csvfile:
        reader = csv.DictReader(csvfile)
        fieldnames = ['pid', 'firstname', 'lastname', 'dob', 'position']
        writer = csv.DictWriter(open('playersnew.csv', 'w'), fieldnames=fieldnames)
        writer.writeheader()
        for row in reader:
            writer.writerow({'pid':row['ilkid'], 'firstname':row['firstname'],
                             'lastname':row['lastname'], 'dob':row['birthdate'],
                             'position':row['position']})
    csvfile.close()

def teamdata():
    with open("teams.csv") as csvfile:
        reader = csv.DictReader(csvfile)
        fieldnames = ['tid', 'name', 'location', 'league']
        writer = csv.DictWriter(open('teamsnew.csv', 'w'), fieldnames=fieldnames)
        writer.writeheader()
        for row in reader:
            writer.writerow({'tid':row['team'], 'name':row['name'], 'location':row['location'],
                             'league':row['leag']})
        csvfile.close()

def coachdata():
    with open("coaches_career.csv") as csvfile:
        reader = csv.DictReader(csvfile)
        fieldnames = ['cid', 'firstname', 'lastname', 'wins', 'losses']
        writer = csv.DictWriter(open('coaches.csv', 'w'), fieldnames=fieldnames)
        writer.writeheader()
        for row in reader:
            writer.writerow({'cid':row['coachid'], 'firstname':row['firstname'],
                             'lastname':row['lastname'], 'wins':row['season_win'],
                             'losses':row['season_loss']})
        csvfile.close()

def coachseasondata():
    with open("coaches_data.csv") as csvfile:
        reader = csv.DictReader(csvfile)
        fieldnames = ['year', 'cid', 'tid', 'win', 'loss']
        writer = csv.DictWriter(open('coachseasons.csv','w'), fieldnames = fieldnames)
        writer.writeheader()
        for row in reader:
            writer.writerow({'year':row['year'], 'cid':row['ï»¿coachid'],
                             'tid':row['team'], 'win':row['season_win'],
                             'loss':row['season_loss']})
        csvfile.close()


def playerseasondata():
    with open("player_season.csv") as csvfile:
        reader = csv.DictReader(csvfile)
        fieldnames = ['year', 'pid', 'tid', 'mvp', 'ppg', 'pnum']
        writer = csv.DictWriter(open('playerseasons.csv','w'), fieldnames = fieldnames)
        writer.writeheader()
        for row in reader:
            writer.writerow({'year':row['year'], 'cid':row['ilkid'],
                             'tid':row['team'], 'win':row['season_win'],
                             'ppg':row['ppg'], 'pnum':row['pnum']})
        csvfile.close()  

def main():
    #playerdata()
    #teamdata()
    #coachdata()
    coachseasondata()

main()
