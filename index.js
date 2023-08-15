const moment = require('moment');

// Get the current date and time
const currentDateTime = moment();

// Set the time to midnight (start of next day)
const nextDayDateTime = currentDateTime.add(1, 'days').startOf('day');

// Format the date and time 
const formattedNextDay = nextDayDateTime.format('YYYY-MM-DDTHH:mm:ss');

console.log(formattedNextDay);

// - git commit --date="YYYY-MM-DDTHH:MM:SS" to commit on particular date

// Logic for autoCommit in future
// - get the current date, using moment js
// - make it midnight 12 so it is next day
// - make a commit(git add ., git commit, git push) on this date, and mark flag as true after it is committed
// - if flag is true then no commit
// - mark flag as false at 11:59 again
