const getDateFromString = (str) => {
    return new Date(str);
  };
  function populateCurrentIncome(incomeorexpense, parent) {

    for (let ioe of incomeorexpense) {
      let time = getDateFromString(ioe.time);
      let li = document.createElement("li");
      let i = document.createElement("i");
      let text1 = document.createTextNode(ioe.amount + "$ " + ioe.origin + ", ");
      let text2 = document.createTextNode(dayOfTheWeeks(time.getUTCDay()) + " " + time.getDate());
  
      li.setAttribute('class', `list-group-item`);
      i.setAttribute('class', `day`);
  
      i.appendChild(text2);
      li.appendChild(text1);
      li.appendChild(i);
      parent.appendChild(li);
    }
  }
function dayOfTheWeeks (number){
    if (number == 0){
        return "Sunday";
        }
    if (number == 1){
        return "Monday";
    }
    if (number == 2){
        return "Tuesday";}
    if (number == 3){
        return "Wednesday";}
    if (number == 4){
        return "Thursday";}
    if (number == 5){
        return "Friday";}
    if (number == 6){
        return "Saturday";}
}
console.log(new Date());

window.onload = async function(evt) {
    evt.preventDefault();
    console.log("Getting Data...");
    Promise.all([
        axios.get("http://localhost:8080/incomes"),
        axios.get("http://localhost:8080/incomes?period=last"),
        axios.get("http://localhost:8080/expenses"),
        axios.get("http://localhost:8080/expenses?period=last")
    ]).then(function(res){
      console.log(res);
      populateCurrentIncome(res[0].data, incomeCurrent);
      populateCurrentIncome(res[1].data, incomeLast);
      populateCurrentIncome(res[2].data, expenseCurrent);
      populateCurrentIncome(res[3].data, expenseLast);
    }).catch(error => console.error('Error in one of the requests: ', error));
  }

const centerForm = document.querySelector("#centerForm");
const incomeCurrent = document.querySelector('#incomeCurrent');
const incomeLast = document.querySelector('#incomeLast');
const expenseCurrent = document.querySelector('#expenseCurrent');
const expenseLast = document.querySelector('#expenseLast');

centerForm.onsubmit = async function(evt) {
  evt.preventDefault();
  const sentence = document.querySelector("#inputForm").value;
  let parts = sentence.split(" ");
  let url = "";
  let obj = {};
  if (parts[1] == "bought"){
     obj = {
        "time": new Date().toISOString().substring(0, 22),
        "origin": parts[parts.length-1],
        "amount": parts[2].substring(0, parts[2].length-1)
      }
      url = "http://localhost:8080/expenses";
  } else if (parts[1] == "earned"){
     obj = {
        "time": new Date().toISOString().substring(0, 22),
        "origin": parts[parts.length-1],
        "amount": parts[2].substring(0, parts[2].length-1)
      }
      url = "http://localhost:8080/incomes";  
  }
  console.log(obj);
  axios.post(url, obj, {
    authorization: {
      'x-api-key': 'K7CHRL6aqt1C6eGJ9EHyFaZCn86G0fyI2sTZKSkW'
    }
  }).then((res) => {
    console.log(res);
    window.location.reload();
  })
}





