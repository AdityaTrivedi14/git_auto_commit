const moment = require("moment");
const simpleGit = require("simple-git");
const jsonfile = require("jsonfile");

const git = simpleGit();

const file = "./data.json";

function gitAutoCommit() {
  try {
    // Get the current date and time
    const currentDateTime = moment();
    // Set the time to midnight (start of next day)
    const testDate = currentDateTime.subtract(5, "days").format() // this is for testing
    const nextDayDateTime = currentDateTime.add(1, "days").startOf("day");
    // Format the date and time
    const formattedNextDay = nextDayDateTime.format("YYYY-MM-DDTHH:mm:ss");

    console.log(formattedNextDay);
    console.log(testDate);

    let streakDay = 0;

    const obj = {
      commitDate: testDate, // testDate to be replaced after testing
      streak: streakDay,
    };

    jsonfile.writeFile(file, obj, () => {
        try {
          // Add all changes
          git.add(file);
          // Commit with a message
          git.commit("test commit", { "--date": testDate });  // commit date will also be changed
          // Push the changes
          git.push("origin", "main");

          console.log("Changes committed and pushed successfully");
        } catch (error) {
          console.error("Error occurred in jsonfile.writeFile", error);
        }
    });

  } catch (error) {
    console.error("Error occurred in gitAutoCommit()", error);
  }
}

gitAutoCommit()

// - git commit --date="YYYY-MM-DDTHH:MM:SS" to commit on particular date

// Logic for autoCommit in future
// - get the current date, using moment js
// - make it midnight 12 so it is next day
// - make a commit(git add ., git commit, git push) on this date, and mark flag as true after it is committed
// - if flag is true then no commit
// - mark flag as false at 11:59 again
