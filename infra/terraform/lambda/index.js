const axios = require('axios');

exports.handler = async (event) => {
  console.log("Event:", JSON.stringify(event, null, 2));
  const records = event.Records || [];
  for (const r of records) {
    try {
      const body = JSON.parse(r.body);
      console.log("Processing message:", body);

      if (body.productoId) {
        await axios.post(`${process.env.PRODUCTO_URL}/producto/${body.productoId}/reserve`, { cantidad: body.cantidad });
      }

      if (body.usuarioId) {
        await axios.post(`${process.env.USUARIO_URL}/notify`, { usuarioId: body.usuarioId, message: "Tu compra está en proceso" });
      }

    } catch (err) {
      console.error("Error processing record:", err);
      throw err;
    }
  }
  return { status: 'processed', count: records.length };
};
