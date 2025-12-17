document.getElementById("contactForm").addEventListener("submit", async function (event) {
  event.preventDefault();

  const name = document.getElementById("name").value;
  const phone = document.getElementById("phone").value;
  const email = document.getElementById("email").value;

  try {
    const response = await fetch("http://localhost:8080/api/contacts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, phone, email }),
    });

    if (!response.ok) throw new Error("Failed to save contact");

    const data = await response.json();

    // Display QR Code
    document.getElementById("qrSection").style.display = "block";
    document.getElementById("qrImage").src = data.qrCodePath;

    // Clear form
    document.getElementById("contactForm").reset();

    alert("Contact saved successfully!");

  } catch (error) {
    console.error(error);
    alert("Error: " + error.message);
  }
});
