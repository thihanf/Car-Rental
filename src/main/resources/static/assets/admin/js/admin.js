/* ================= COMMON CONFIG ================= */
const rowsToShow = 5;

/* ================= VEHICLES ================= */
let vehicleExpanded = false;

function applyVehicleFilters() {
  const search =
    document.getElementById("searchInput")?.value.toLowerCase() || "";
  const status = document.getElementById("statusFilter")?.value || "";

  const rows = Array.from(document.querySelectorAll("#vehicleTable tr"));
  const dataRows = rows.filter((r) => r.querySelector("td"));

  const filtered = dataRows.filter((row) => {
    const text = row.textContent.toLowerCase();
    const badge = row.querySelector("td:nth-child(5) span");
    const rowStatus = badge ? badge.innerText.trim().toUpperCase() : "";

    return text.includes(search) && (!status || rowStatus === status);
  });

  dataRows.forEach((row, index) => {
    row.style.display =
      filtered.includes(row) && (vehicleExpanded || index < rowsToShow)
        ? ""
        : "none";
  });

  updateToggleButton("toggleRowsBtn", filtered.length, vehicleExpanded);
}

function toggleRows() {
  vehicleExpanded = !vehicleExpanded;
  applyVehicleFilters();
}

/* ================= CUSTOMERS ================= */
let customerExpanded = false;

function applyCustomerFilters() {
  const search =
    document.getElementById("searchInput")?.value.toLowerCase() || "";
  const status = document.getElementById("statusFilter")?.value || "";

  const rows = Array.from(document.querySelectorAll("#customerTable tr"));
  const dataRows = rows.filter((r) => r.querySelector("td"));

  const filtered = dataRows.filter((row) => {
    const text = row.textContent.toLowerCase();
    const badge = row.querySelector("td:nth-child(5) span");
    const rowStatus = badge ? badge.innerText.trim().toUpperCase() : "";

    return text.includes(search) && (!status || rowStatus === status);
  });

  dataRows.forEach((row, index) => {
    row.style.display =
      filtered.includes(row) && (customerExpanded || index < rowsToShow)
        ? ""
        : "none";
  });

  updateToggleButton("customerToggleBtn", filtered.length, customerExpanded);
}

function toggleCustomerRows() {
  customerExpanded = !customerExpanded;
  applyCustomerFilters();
}

/* ================= BUTTON HANDLER ================= */
function updateToggleButton(buttonId, count, expanded) {
  const btn = document.getElementById(buttonId);
  if (!btn) return;

  if (count <= rowsToShow) {
    btn.style.display = "none";
  } else {
    btn.style.display = "inline-block";
    btn.textContent = expanded ? "Show Less" : "Show More";
  }
}

/* ================= AUTO INIT ================= */
document.addEventListener("DOMContentLoaded", () => {
  if (document.getElementById("vehicleTable")) {
    applyVehicleFilters();
  }

  if (document.getElementById("customerTable")) {
    applyCustomerFilters();
  }

  if (document.getElementById("userTable")) {
    applyUserFilters();
  }
  if (document.getElementById("bookingTable")) {
    applyBookingFilters();
  }
});

/* ================= USERS ================= */
let userExpanded = false;

function applyUserFilters() {
  const search =
    document.getElementById("userSearchInput")?.value.toLowerCase() || "";
  const role = document.getElementById("roleFilter")?.value || "";
  const status = document.getElementById("statusFilter")?.value || "";

  const rows = Array.from(document.querySelectorAll("#userTable tr"));
  const dataRows = rows.filter((r) => r.querySelector("td"));

  const filtered = dataRows.filter((row) => {
    const username = row.children[1]?.innerText.toLowerCase() || "";
    const email = row.children[2]?.innerText.toLowerCase() || "";

    const roleBadge = row.children[4]?.innerText.trim();
    const statusBadge = row.children[5]?.innerText.trim();

    const matchesSearch = username.includes(search) || email.includes(search);

    const matchesRole = !role || roleBadge === role;

    const matchesStatus = !status || statusBadge === status;

    return matchesSearch && matchesRole && matchesStatus;
  });

  dataRows.forEach((row, index) => {
    row.style.display =
      filtered.includes(row) && (userExpanded || index < rowsToShow)
        ? ""
        : "none";
  });

  updateToggleButton("userToggleBtn", filtered.length, userExpanded);
}

function toggleUserRows() {
  userExpanded = !userExpanded;
  applyUserFilters();
}

/* ================= BOOKINGS ================= */
let bookingExpanded = false;

function applyBookingFilters() {
  const search =
    document.getElementById("searchInput")?.value.toLowerCase() || "";
  const status = document.getElementById("statusFilter")?.value || "";

  const rows = Array.from(document.querySelectorAll("#bookingTable tr"));
  const dataRows = rows.filter((r) => r.querySelector("td"));

  const filtered = dataRows.filter((row) => {
    // Search in ID (col 1), Vehicle (col 2), and Customer (if you add it back)
    const text = row.textContent.toLowerCase();

    // Status is in the 5th column (index 4)
    const badge = row.querySelector("td:nth-child(5) span");
    const rowStatus = badge ? badge.innerText.trim().toUpperCase() : "";

    const matchesSearch = text.includes(search);
    const matchesStatus = !status || rowStatus === status;

    return matchesSearch && matchesStatus;
  });

  dataRows.forEach((row, index) => {
    // Show row if it passes filter AND (we are expanded OR it's within the first 5)
    const isVisible = filtered.includes(row);
    const withinLimit = index < rowsToShow;

    row.style.display =
      isVisible && (bookingExpanded || withinLimit) ? "" : "none";
  });

  updateToggleButton("bookingToggleBtn", filtered.length, bookingExpanded);
}

function toggleBookingRows() {
  bookingExpanded = !bookingExpanded;
  applyBookingFilters();
}
