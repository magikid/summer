# summer

An example project for the Summer 2019 Code Louisville Java class.

## Description

This project will read in two data sets, collate them, print them to STDOUT, and
write them to disk.  The two data sets that I've chosen are [UFO Sightings around the world](https://www.kaggle.com/camnugent/ufo-sightings-around-the-world) 
and the part of the [Salem Witchcraft Dataset](https://www.kaggle.com/rtatman/salem-witchcraft-dataset) that contains the accused witches and the 
months they were accused.  The program will read in both data sets and ask the
user to choose how to collate the data.  The options are to either print all of
the witches accused in the same town where a UFO sighting occurred or to print
all of the UFO sightings that occurred in the same month a witch was accused
(regardless of year).

## Getting Started
### Dependencies

* JDK 12
* Maven

### Installing

1. Clone the project
  ```bash
  git clone https://github.com/magikid/summer.git
  cd summer
  ```
2. Run the project using maven
  ```
  mvn clean package exec:java -Dexec.mainClass=dev.foobarbaz.App
  ```

## Help

If you run into any problems using this project, please open an issue.

## Authors

* Chris Jones
  [email](mailto:chris@christopherjones.us)

## License

This project is licensed under the GPL v3 or later license.  See the LICENSE
file for more details.

## Acknowledgments

* [DomPizzie](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [PurpleBooth](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)

