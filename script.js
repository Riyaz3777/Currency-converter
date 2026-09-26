var CURRENCIES = {
  USD: "US Dollar", EUR: "Euro", GBP: "British Pound", INR: "Indian Rupee",
  JPY: "Japanese Yen", AUD: "Australian Dollar", CAD: "Canadian Dollar",
  CHF: "Swiss Franc", CNY: "Chinese Yuan", SGD: "Singapore Dollar",
  AED: "UAE Dirham", NZD: "New Zealand Dollar", ZAR: "South African Rand", SEK: "Swedish Krona"
};

var amountEl = document.getElementById('amount');
var fromEl = document.getElementById('fromCurrency');
var toEl = document.getElementById('toCurrency');
var resultEl = document.getElementById('resultValue');
var statusEl = document.getElementById('status');
var tickerEl = document.getElementById('ticker');
var swapBtn = document.getElementById('swap');

function populateSelect(select, selected) {
  Object.keys(CURRENCIES).forEach(function (code) {
    var opt = document.createElement('option');
    opt.value = code;
    opt.textContent = code + ' — ' + CURRENCIES[code];
    if (code === selected) opt.selected = true;
    select.appendChild(opt);
  });
}

populateSelect(fromEl, 'USD');
populateSelect(toEl, 'INR');

var debounceTimer = null;
function scheduleConvert() {
  clearTimeout(debounceTimer);
  debounceTimer = setTimeout(convert, 250);
}

function formatNumber(n) {
  return n.toLocaleString(undefined, { maximumFractionDigits: 2, minimumFractionDigits: 2 });
}

function convert() {
  var amount = parseFloat(amountEl.value);
  var from = fromEl.value;
  var to = toEl.value;

  if (isNaN(amount)) {
    resultEl.textContent = '—';
    statusEl.textContent = 'Enter a valid amount.';
    statusEl.className = 'status error';
    return;
  }

  if (from === to) {
    resultEl.textContent = formatNumber(amount);
    statusEl.textContent = 'Same currency selected.';
    statusEl.className = 'status';
    tickerEl.textContent = '';
    return;
  }

  statusEl.textContent = 'Fetching latest rate…';
  statusEl.className = 'status';

  var url = 'https://api.frankfurter.app/latest?amount=' + encodeURIComponent(amount) +
            '&from=' + encodeURIComponent(from) + '&to=' + encodeURIComponent(to);

  fetch(url)
    .then(function (res) {
      if (!res.ok) throw new Error('Request failed (' + res.status + ')');
      return res.json();
    })
    .then(function (data) {
      var value = data.rates[to];
      resultEl.textContent = formatNumber(value);
      var rate = value / amount;
      tickerEl.textContent = '1 ' + from + ' = ' + formatNumber(rate) + ' ' + to;
      statusEl.textContent = 'Rates as of ' + data.date + ' · European Central Bank';
      statusEl.className = 'status';
    })
    .catch(function () {
      resultEl.textContent = '—';
      statusEl.textContent = 'Could not fetch rates. Check your connection and try again.';
      statusEl.className = 'status error';
    });
}

amountEl.addEventListener('input', scheduleConvert);
fromEl.addEventListener('change', convert);
toEl.addEventListener('change', convert);

swapBtn.addEventListener('click', function () {
  var f = fromEl.value;
  fromEl.value = toEl.value;
  toEl.value = f;
  swapBtn.classList.add('spin');
  setTimeout(function () { swapBtn.classList.remove('spin'); }, 200);
  convert();
});

convert();
