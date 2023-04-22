let counter = 1;

function addTable() {
  const newTable = document.createElement("table");
  newTable.classList.add("table");
  newTable.style.marginTop = "10px";

//이름
  const tableRow1 = document.createElement("tr");
  const nameHeader = document.createElement("th");
  const nameLabel = document.createElement("label");
  nameLabel.setAttribute("for", `visitor-name-${counter}`);
  nameLabel.innerText = "이름";
  const nameInput = document.createElement("input");
  nameInput.setAttribute("type", "text");
  nameInput.classList.add("form-control");
  nameInput.setAttribute("id", `visitor-name-${counter}`);
  nameInput.setAttribute("name", "VISITOR");
  nameInput.required = true;
  nameHeader.appendChild(nameLabel);
  tableRow1.appendChild(nameHeader);
  const nameData = document.createElement("td");
  nameData.appendChild(nameInput);
  tableRow1.appendChild(nameData);
//업체명
  const tableRow2 = document.createElement("tr");
  const companyHeader = document.createElement("th");
  const companyLabel = document.createElement("label");
  companyLabel.setAttribute("for", `visit-company-${counter}`);
  companyLabel.innerText = "업체명";
  const companyInput = document.createElement("input");
  companyInput.setAttribute("type", "text");
  companyInput.classList.add("form-control");
  companyInput.setAttribute("id", `visit-company-${counter}`);
  companyInput.setAttribute("name", "visitAssign");
  companyHeader.appendChild(companyLabel);
  tableRow2.appendChild(companyHeader);
  const companyData = document.createElement("td");
  companyData.appendChild(companyInput);
  tableRow2.appendChild(companyData);
  const classHeader = document.createElement("th");
  const classLabel = document.createElement("label");
  classLabel.setAttribute("for", `visit-class-${counter}`);
//직급
  classLabel.innerText = "직급";
  const classInput = document.createElement("input");
  classInput.setAttribute("type", "text");
  classInput.classList.add("form-control");
  classInput.setAttribute("id", `visit-class-${counter}`);
  classInput.setAttribute("name", "VISIT_CLASS");
  classHeader.appendChild(classLabel);
  tableRow2.appendChild(classHeader);
  const classData = document.createElement("td");
  classData.appendChild(classInput);
  tableRow2.appendChild(classData);
//전화번호
  const tableRow3 = document.createElement("tr");
  const phoneHeader = document.createElement("th");
  const phoneLabel = document.createElement("label");
  phoneLabel.setAttribute("for", `visit-phone-${counter}`);
  phoneLabel.innerText = "전화번호";
  const phoneInput = document.createElement("input");
  phoneInput.setAttribute("type", "tel");
  phoneInput.classList.add("form-control");
  phoneInput.setAttribute("id", `visit-phone-${counter}`);
  phoneInput.setAttribute("name", "VISITOR_PHONE");
 //이메일
  const emailHeader = document.createElement("th");
  const emailLabel = document.createElement("label");
  emailLabel.setAttribute("for", `visit-email-${counter}`);
  emailLabel.innerText = "이메일";
  const emailInput = document.createElement("input");
  emailInput.setAttribute("type", "email");
  emailInput.classList.add("form-control");
  emailInput.setAttribute("id", `visit-email-${counter}`);
  emailInput.setAttribute("name", "VISITOR_EMAIL");
  phoneHeader.appendChild(phoneLabel);
  tableRow3.appendChild(phoneHeader);
  const phoneData = document.createElement("td");
  phoneData.appendChild(phoneInput);
  tableRow3.appendChild(phoneData);
  emailHeader.appendChild(emailLabel);
  tableRow3.appendChild(emailHeader);
  const emailData = document.createElement("td");
  emailData.appendChild(emailInput);
  tableRow3.appendChild(emailData);

// 삭제 버튼
  const tableRow4 = document.createElement("tr");
  const minusButtonData = document.createElement("td");
  minusButtonData.setAttribute("colspan", "4");
  minusButtonData.setAttribute("style", "text-align: right; width: 100%");
  const minusButton = document.createElement("button");
  minusButton.setAttribute("class", "btn minus-button");
  minusButton.setAttribute("id", `minusBtn-${counter}`);
  minusButton.setAttribute("onclick", "removeTable(this)");
  minusButton.innerText = "삭제";
  minusButtonData.appendChild(minusButton);
  tableRow4.appendChild(minusButtonData);


  newTable.appendChild(tableRow1);
  newTable.appendChild(tableRow2);
  newTable.appendChild(tableRow3);
  newTable.appendChild(tableRow4);

  const tables = document.getElementById("tables");
  tables.appendChild(newTable);

  // counter를 증가
  counter++;
  }

//테이블 삭제 함수
  function removeTable(button) {
  const tableRow = button.parentNode.parentNode;
  const table = tableRow.parentNode.parentNode;
  table.removeChild(tableRow.parentNode);
  }


//기타일 경우 상세 입력 필수로 하기
  var select = document.getElementById("purpose");

  // select element 변경 감지
  select.addEventListener("change", function() {
    if (select.value === "기타") {
      // textarea element 가져오기
      var textarea = document.getElementById("content");

      // 필수입력 설정
      textarea.required = true;
    } else {
      // textarea 필수입력 해제
      document.getElementById("content").required = false;
    }
  });

  //등록 여부 확인
  function confirmSubmit() {
   return confirm("등록하시겠습니까?");
  }