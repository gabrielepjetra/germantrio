# 💻 Product Visie
## 1. Product beschrijving.
Dit is een website die ik gemaakt heb voor mensen die moelijk een keuze kunnen maken bij het kiezen van een van de 3 grootste en belangrijkste Duitse merken (Audi, BMW en Mercedes-Benz)
* Wat? Ik maak deze website om de verschillende modellen te bekijken en vergelijken.
* Voor wie? Voor mensen die geen verduidelijking kunnen online krijgen voor hun keuze.
* Waarom? Ik had het ook moeilijk bij het kiezen van mijn auto, en aangezien dat het geen t-shirt of jas is dat je kunt terug brengen, had ik graag zo een website gehad toen ik het nodig had.

## 2. User story mapping
![User story map](readme/images/user_story_map.jpg)

## 3. Personas
### 1) Lena - De praktische premium-zoeker
![UML diagram](readme/images/Lena.png)
* **Gebruikersgroep:** Gezinnen en praktisch ingestelde kopers die op zoek zijn naar een betrouwbare premium SUV (zoals de Audi Q3, BMW X1 of Mercedes-Benz GLA).
* **Behoeften:** Transparante, direct vergelijkbare data over interieurruimte (kofferbakinhoud in liters), brandstofverbruik en standaard aanwezige veiligheidssystemen.
* **Doelen:** Een veilige, ruime auto vinden die past bij het dagelijkse gezinsleven, zonder onnodig geld uit te geven aan dure pret-pakketten.
* **Pijnpunten:** Ondoorzichtige en complexe optielijsten van Duitse merken, en de angst voor verborgen of hoge onderhoudskosten.
### 2) Felix - De prestatie-purist
![UML diagram](readme/images/Felix.png)
* **Gebruikersgroep:** Autoliefhebbers en sportieve rijders die puur gefocust zijn op rijdynamiek en harde cijfers (zoals de BMW M240i, Audi RS3 of Mercedes-AMG CLA 45).
* **Behoeften:** Toegang tot rauwe, gedetailleerde technische specificaties in één overzicht (pk's, koppel, 0-100 km/u tijden, type aandrijflijn en gewicht).
* **Doelen:** De best presterende en meest dynamische auto binnen het budget vinden om de ultieme rijbeleving te garanderen.
* **Pijnpunten:** Gefrustreerd door marketingpraatjes en lifestyle-foto's op dealerwebsites; het kost te veel tijd om technische data op te graven en naast elkaar te leggen.
### 3) Marcus - De tech-gedreven exec
![UML diagram](readme/images/Marcus.png)
* **Gebruikersgroep:** Zakelijke veelrijders (leaserijders) die de overstap maken naar het hogere segment elektrische auto's (zoals de Mercedes-Benz EQE, BMW i5 of Audi A6 e-tron).
* **Behoeften:** Duidelijk inzicht in accucapaciteit (kWh), DC-snellaadtijden (10-80%), werkelijke actieradius en de nieuwste rijhulpsystemen/infotainment.
* **Doelen:** De ultieme, toekomstbestendige en comfortabele elektrische reisauto selecteren voor lange zakelijke ritten.
* **Pijnpunten:** Tijdgebrek voor bezoeken aan meerdere fysieke showrooms en een absolute hekel aan trage, onoverzichtelijke software in auto's.

# Conceptueel Model
### Dit is hoe dat mijn web app gaat werken

In deze applicatie staan automerken en automodellen centraal. Een **Brand** stelt een merk voor, zoals Audi, BMW of Mercedes-Benz. Een **Brand** heeft meerdere **CarModel** objecten. Elk **CarModel** hoort bij precies ��n **Brand** en bevat technische gegevens zoals motor, pk, koppel, aandrijving, gewicht, 0-100 tijd en basisprijs.

Een **AppUser** is een geregistreerde gebruiker. Een gebruiker kan meerdere automodellen opslaan als favoriete auto's. Omdat meerdere gebruikers dezelfde auto kunnen opslaan, is dit een many-to-many relatie tussen **AppUser** en **CarModel**.

Bij een **CarModel** kunnen gebruikers reacties plaatsen. Een **Comment** hoort altijd bij ��n gebruiker en ��n automodel. Een comment kan ook antwoorden hebben. Die antwoorden zijn opnieuw comments, maar met een parent comment. Zo blijft het systeem eenvoudig: een hoofdcomment kan meerdere replies hebben.

Een **Notification** wordt gebruikt om gebruikers te verwittigen, bijvoorbeeld wanneer iemand antwoordt op hun comment of wanneer een admin een globale melding stuurt. Een notification hoort bij ��n gebruiker en kan optioneel verwijzen naar een automodel.

Een **BlacklistedEmail** is een e-mailadres dat niet meer mag registreren. Admins kunnen gebruikers beheren. Ze kunnen een gebruiker bannen, waardoor die niet meer kan inloggen, of tijdelijk muten, waardoor die gebruiker geen comments of replies kan plaatsen.

![Conceptueel model](readme/images/conceptueel_diagram_germantrio.png)

# 💻 Functionale analyse
## 1. Wireframes
### Dit is hoe mijn homepagina ongeveer eruit gaat zien.
![Homepage wireframe](readme/images/wireframe_homepage.jpg)
### Dit is hoe ik wil dat mijn compare pagina ongeveer eruit ziet.
![Comparator tool page wireframe](readme/images/wireframe_compare.jpg)

## 2 Use case diagram

![Usecase diagram](readme/images/usecase_diagram_germantrio.png)

## 3 Activity diagram

### Twee auto's vergelijken

![Vergelijking van 2 autos](readme/images/activity_vergelijken_germantrio.png)

### Comment, reply en notificatie

![Comment, reply en notificatie](readme/images/activity_comment_germantrio.png)

## 4 Class diagram

![Class diagram](readme/images/class_diagram_germantrio.png)
